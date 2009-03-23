package ch.panter.demo.strategy;

import java.util.ArrayList;
import java.util.List;

import ch.panter.demo.strategy.impl.LoggingOutputStrategy;
import ch.panter.demo.strategy.impl.NoopOutputStrategy;
import ch.panter.demo.strategy.impl.PopupOutputStrategy;
import ch.panter.demo.strategy.impl.StderrOutputStrategy;
import ch.panter.demo.strategy.impl.StdoutOutputStrategy;
import ch.panter.demo.strategy.impl.TimeOutputStrategy;
import ch.panter.demo.strategy.impl.XMLOuputStrategy;

/**
 * Factory to register and retrieve output strategies
 * 
 * @author bseelige
 *
 */
public class OutputStrategyFactory {
	
	
	/** list holding all available output strategies */
	static List<OutputStrategy> strategies = new ArrayList<OutputStrategy>();
	
	/** a human readable description of all output strategies */
	static String description = "";
	
	/** initial list out output strategies available */
	static {
		addStrategy(NoopOutputStrategy.class);
		addStrategy(StdoutOutputStrategy.class);
		addStrategy(StderrOutputStrategy.class);
		addStrategy(XMLOuputStrategy.class);
		addStrategy(LoggingOutputStrategy.class);
		addStrategy(TimeOutputStrategy.class);
		addStrategy(PopupOutputStrategy.class);
	}
	
	/** @return strategy with given id */
	public static OutputStrategy getStrategy(int id) {
		return strategies.get(id);
	}
	
	/** 
	 * adds a new output strategy
	 *
	 * instantiates strategy and adds it to description
	 * 
	 */
	public static void addStrategy(Class clazz) {
		try {
			strategies.add((OutputStrategy) clazz.newInstance());
			description = description + (strategies.size()-1) +": "
				+clazz.getSimpleName()+"\n";
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}

	/** @return human readable description of all output strategies */
	public static String getDescription() {
		return description;
		
	}
	

}
