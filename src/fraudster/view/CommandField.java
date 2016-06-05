package fraudster.view;

import javax.swing.*;

/**
 * Essentially a JTextField with registers so I can transfer data stealthily without using a bunch of globals in MainFrame
 * it doesn't perform checks on its own
 */
public class CommandField extends JTextField
{
	private Integer minVal, maxVal;
	private Boolean numbersOnly;
	public String source;
	
	public CommandField()
	{
		super();
	}
	
	public Integer getMin()
	{
		return minVal;
	}
	public void setMin(Integer m)
	{ minVal = m; }
	
	public Integer getMax()
	{
		return maxVal;
	}
	public void setMax(Integer m)
	{ maxVal = m; }
	
	public Boolean getNumOnly()
	{
		return numbersOnly;
	}
	public void setNumOnly(Boolean n)
	{ numbersOnly = n; }
	
	/**
	 * no more restrictions
	 */
	public void reset()
	{
		source=null;
		minVal = maxVal = null;
		numbersOnly = false;
	}
}