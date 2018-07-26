package com.company.testss13.support.page;
/**
 * 
 * @author Allen
 *将每个线程的分页参数封装起来
 *
 */
public class PageSystemContext {
	private static ThreadLocal<Integer> pageNum=new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> pageSize =new ThreadLocal<Integer>();
	public static int getPageNum() {
		Integer os = pageNum.get();
		if(os == null){
			return 0;
		}
		return os;
	}
	public static void setPageNum(int _offset) {
		pageNum.set(_offset);
	}
	public static void removePageNum(){
		pageNum.remove();
	}
	
	public static int getPageSize() {
		Integer ps = pageSize.get();
		if(ps == null){
			return Integer.MAX_VALUE;
		}
		return ps;
	}
	public static void setPageSize(int _pageSize) {
		pageSize.set(_pageSize);
	}
	public static void removePageSize(){
		pageSize.remove();
	}
}
