package test.visualization;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Stroke;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;


import edu.uci.ics.jung.algorithms.layout.SpringLayout2;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.decorators.NumberFormattingTransformer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

public class TestVis {

	   private final static class EdgeWeightStrokeFunction<E>
	    implements Transformer<E,Stroke>
	    {
	        protected static final Stroke basic = new BasicStroke(1);
	        protected static final Stroke heavy = new BasicStroke(2);
	        protected static final Stroke dotted = RenderContext.DOTTED;
	        
	        protected boolean weighted = false;
	        protected Map<E,Number> edge_weight;
	        
	        public EdgeWeightStrokeFunction(Map<E,Number> edge_weight)
	        {
	            this.edge_weight = edge_weight;
	        }
	        
	        public void setWeighted(boolean weighted)
	        {
	            this.weighted = weighted;
	        }
	        
	        public Stroke transform(E e)
	        {
	            if (weighted)
	            {
	                if (drawHeavy(e))
	                    return heavy;
	                else
	                    return dotted;
	            }
	            else
	                return basic;
	        }
	        
	        protected boolean drawHeavy(E e)
	        {
	            double value = edge_weight.get(e).doubleValue();
	            if (value > 0.7)
	                return true;
	            else
	                return false;
	        }
	        
	    }
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		Graph<Integer, Double> g = new DirectedSparseGraph<Integer, Double>();

	    g.addVertex((Integer) 1);
	    g.addVertex((Integer) 2);
	    g.addVertex((Integer) 3);

	    g.addEdge(0.543, 1, 2);
	    g.addEdge(0.0004, 2, 3);
	    g.addEdge(1.0, 1, 3);
	    
	    

	    SpringLayout2<Integer, String> layout = new SpringLayout2(g);

	    layout.setSize(new Dimension(350, 350));
	    BasicVisualizationServer<Integer, String> vv = new BasicVisualizationServer(layout);
	    vv.setPreferredSize(new Dimension(350, 350)); //Sets the viewing area size
        vv.getRenderContext().setVertexLabelTransformer(new Transformer<Integer,String>(){

			public String transform(Integer v) {
				return "Vertex "+v;
			}});
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<String>());      	
	    
	    
//	    Map<Number,Number> edge_weight = new HashMap<Number,Number>();
//	    EdgeWeightStrokeFunction ewcs = 
//            new EdgeWeightStrokeFunction<Number>(edge_weight);
//	    vv.getRenderContext().setEdgeStrokeTransformer(ewcs);
//	    
	    
	    JFrame frame = new JFrame("Simple Graph View");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().add(vv);
	    frame.pack();
	    frame.setVisible(true);

		

	}

}
