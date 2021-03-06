package mcts.datastructure;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

public class PBBMNodeTest {

	private PBBMNode parentNode;
	private PBBMNode childNode1;
	private PBBMNode childNode2;
	private PBBMNode childNode3;
	private PBBMNode childOf3Node1;
	private PBBMNode childOf3Node2;
	
	@Before
	public void setUp() throws Exception {
		ChoiceSet choiceSetMock = mock(ChoiceSet.class);
		
		parentNode = new PBBMNode(null, choiceSetMock);
		childNode1 = new PBBMNode(parentNode, choiceSetMock);
		childNode2 = new PBBMNode(parentNode, choiceSetMock);
		childNode3 = new PBBMNode(parentNode, choiceSetMock);
		childOf3Node1 = new PBBMNode(childNode3, choiceSetMock);
		childOf3Node2 = new PBBMNode(childNode3, choiceSetMock);
	}

	@Test
	public void testGetUrgency() {
		childNode1.backPropagate(-1); //sd 0.5
		childNode2.backPropagate(1); //sd 2
		childNode2.backPropagate(5);
		childNode3.backPropagate(0.5); //sd 
		childOf3Node1.backPropagate(0);
		childOf3Node2.backPropagate(1);
		
		assertEquals(0.0371507, childNode1.getUrgency((OMCVariantNode) childNode1.getMaxSibling()), 0.00001);
		assertEquals(1, childNode2.getUrgency((OMCVariantNode) childNode2.getMaxSibling()), 0.00001);
		assertEquals(0.125123, childNode3.getUrgency((OMCVariantNode) childNode3.getMaxSibling()), 0.00001);
		assertEquals(0.0907180, childOf3Node1.getUrgency((OMCVariantNode) childOf3Node1.getMaxSibling()), 0.00001);
		assertEquals(1, childOf3Node2.getUrgency((OMCVariantNode) childOf3Node2.getMaxSibling()), 0.00001);
		
	}

}
