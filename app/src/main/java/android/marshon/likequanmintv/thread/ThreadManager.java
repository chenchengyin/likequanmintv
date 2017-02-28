package android.marshon.likequanmintv.thread;

/**
 * @类名: ThreadManager
 * @创建者: Marshon.Chen
 * @创建时间: 2015-5-6 上午9:42:57
 * @描述: 线程池的管理
 * 
 * @更新时间: $Date: 2015-05-06 09:57:03 +0800 (Wed, 06 May 2015) $
 */
public class ThreadManager
{
	private static ThreadPoolProxy	mLongPool;
	private static Object			mLongLock	= new Object();

	/**
	 * 获得耗时操作的线程池
	 * 
	 * @return
	 */
	public static ThreadPoolProxy getLongPool()
	{
		if (mLongPool == null)
		{
			synchronized (mLongLock)
			{
				if (mLongPool == null)
				{
					mLongPool = new ThreadPoolProxy(5, 5, 0);
				}
			}
		}
		return mLongPool;
	}
}
