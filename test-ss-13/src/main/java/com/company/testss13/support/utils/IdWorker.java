package com.company.testss13.support.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: IdWorker.java
 * @Author Allen allen.ime@gmail.com
 * @Date 2014年5月26日 下午12:06:40
 * @Description: 核心代码为其IdWorker这个类实现，其原理结构如下，我分别用一个0表示一位，用—分割开部分的作用： 1
 *               0---0000000000 0000000000 0000000000 0000000000 0
 *               ---00000---00000 ---0000000000 00
 *               在上面的字符串中，第一位为未使用（实际上也可作为long的符号位），接下来的41位为毫秒级时间 ，
 *               然后5位datacenter标识位，5位机器ID（并不算标识符，实际是为线程标识），然后12位该毫秒内的当前毫秒内的计数，
 *               加起来刚好64位，为一个Long型。这样的好处是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞
 *               （由datacenter和机器ID作区分），并且效率较高，经测试，snowflake每秒能够产生26万ID左右，完全满足需要。
 */
public class IdWorker {
	private static final Logger logger = LoggerFactory.getLogger(IdWorker.class);

	public static final Integer DEFAULT_WORKERID = Integer.parseInt(System.getProperty("system.zone", "0"));

	private final long workerId;
	// private final long datacenterId;
	/** 开始时间 2015-10-01 00：00：00 */
	private final static long twepoch = 1443628800000L;
	/** 序号 */
	private long sequence = 0L;
	// 机器标识位数
	private final static long workerIdBits = 5L;
	// 机器ID最大值
	public final static long maxWorkerId = -1L ^ -1L << workerIdBits;
	// 毫秒内自增位
	private final static long sequenceBits = 8L;
	// 机器ID偏左移 位
	private final static long workerIdShift = sequenceBits;
	// 时间毫秒左移位
	private final static long timestampLeftShift = sequenceBits + workerIdBits;

	public final static long sequenceMask = -1L ^ -1L << sequenceBits;

	private long lastTimestamp = -1L;

	private static final ReentrantLock lock = new ReentrantLock();

	public IdWorker(long workerId) {
		logger.debug("maxWorkerId:{},sequenceMask:{} ", maxWorkerId, sequenceMask);
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
		}
		this.workerId = workerId;
	}

	public long nextId() {
		long timestamp = this.timeGen();

		try {
			lock.lock();
			if (this.lastTimestamp == timestamp) {
				// 当前毫秒内，则+1
				this.sequence = (this.sequence + 1) & sequenceMask;
				if (this.sequence == 0) {
					timestamp = this.tilNextMillis(this.lastTimestamp);
				}
			} else {
				this.sequence = 0;
			}
			// 时间错误 修改系统时间到之前时间
			if (timestamp < this.lastTimestamp) {
				try {
					throw new Exception(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", this.lastTimestamp - timestamp));
				} catch (Exception e) {
					logger.error("IdWorker nextId error", e);
				}
			}

			this.lastTimestamp = timestamp;
		} finally {
			if (lock.isLocked() && lock.isHeldByCurrentThread())
				lock.unlock(); // 释放锁
		}
		// ID偏移组合生成最终的ID，并返回ID
		long nextId = (timestamp - twepoch << timestampLeftShift) | (this.workerId << workerIdShift) | (this.sequence);
		return nextId;
	}

	// 等待下一个毫秒的到来
	private long tilNextMillis(final long lastTimestamp) {
		long timestamp = this.timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = this.timeGen();
		}
		return timestamp;
	}

	private long timeGen() {
		return System.currentTimeMillis();
	}

	private static final ConcurrentHashMap<Integer, IdWorker> IDWORKERS = new ConcurrentHashMap<Integer, IdWorker>();

	public static final IdWorker get() {
		return get(DEFAULT_WORKERID);
	}

	public synchronized static final IdWorker get(Integer zone) {
		IdWorker idWorker = IDWORKERS.get(zone);
		if (idWorker == null) {
			idWorker = new IdWorker(zone);
			IDWORKERS.put(zone, idWorker);
		}
		return idWorker;

	}

	public static void main(String[] args) throws ParseException {
		IdWorker idWorker = new IdWorker(1);
		long id = idWorker.nextId();
		logger.debug("id:{}", id);

		id = idWorker.nextId();
		logger.debug("id:{}", id);
		// String ss = "111111111111111111111111111111111111111111";
		// System.err.println(idWorker.nextId());
		// System.out.println(System.currentTimeMillis());

		// 时间转换
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		// Date date = df.parse("2015-10-01 00:00:00");
		// System.out.println(date.getTime());

	}
}