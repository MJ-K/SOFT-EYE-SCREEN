package frame;

public class timeCheck 
{
	public long startTime = 0;
	public long endTime = 0;
	public int first = 0;
	public void start()
	{
		startTime = System.currentTimeMillis();
	}
	public void end()
	{
		endTime = System.currentTimeMillis();
	}
	public void ShowWaitTime()
	{
		System.out.println("걸린시간: "+ (endTime-startTime) +" millisecond");
	}
	
	public boolean Count_X_millisec(long x)
	{
		if(first == 0)
		{
			start();
			first++;
		}
		end();
		if((endTime - startTime) >= x) { return true; }
		else { return false; }
	}
	
	public void Rcount()
	{
		first = 0;
	}
	
}
