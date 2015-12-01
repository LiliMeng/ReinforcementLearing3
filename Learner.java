import java.util.Random;


public class Learner   
{   
  public static final double LearningRate = 0.1;   
  public static final double DiscountRate = 0.9;   
  public static double explorationRate = 0.9;   
  private int lastState;   
  private int lastAction;   
  private boolean first = true;   
  private QTable table;   
   
  public Learner(QTable table)   
  {   
    this.table = table;   
  }   
   
  public void learn(int state, int action, double reinforcement)   
  {   
    System.out.println("Reinforcement: " + reinforcement);   
    if (first)   
      first = false;   
    else   
    {   
      double oldQValue = table.getQValue(lastState, lastAction);   
      double newQValue = (1 - LearningRate) * oldQValue + LearningRate * (reinforcement + DiscountRate * table.getMaxQValue(state));   
      System.out.println("Old Q-Value: " + oldQValue + ", New Q-Value: " + newQValue + ", Different: " + (newQValue - oldQValue));   
      table.setQValue(lastState, lastAction, newQValue);   
    }   
    lastState = state;   
    lastAction = action;   
  }   
  
  public void learnSARSA(int state, int action, double reward)
	{
		double oldQValue = table.getQValue(lastState, lastAction);
		
		int newAction=this.selectAction(state);
		
		double newQValue = oldQValue + LearningRate * (reward+ DiscountRate * table.getQValue(state, newAction)-oldQValue);
	    
		//update the Q value in the look up table
		table.setQValue(lastState, lastAction, newQValue);
		
		//update state and action
		lastState = state;
		lastAction = newAction;
	}
   
  public int selectAction(int state)
  {

		double thres = Math.random();
		
		int actionIndex = 0;
		
		if (thres<explorationRate)
		{//randomly select one action from action(0,1,2)
			Random ran = new Random();
			actionIndex = ran.nextInt(((Action.NumRobotActions-1 - 0) + 1));
		}
		else
		{//e-greedy
			actionIndex=table.getBestAction(state);
		}
		return actionIndex;
	}
  
 
}  
