package unit16_thread.p16_6;

public class DrawThread extends Thread
{
	private Account account;
	private double drawAmount;
	public DrawThread(String name, Account account, double drawAmount) 
	{
		super(name);
		this.account = account;
		this.drawAmount = drawAmount;
	}
	// 重复100次取钱操作
	@Override
	public void run() 
	{
		for (int i = 0; i < 100; i++) 
		{
			System.out.println("第 " + i + "次取钱。");
			account.draw(drawAmount,i);
		}
	}
	
}
