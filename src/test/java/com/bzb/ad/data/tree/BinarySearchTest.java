package com.bzb.ad.data.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinarySearchTest {

  BinarySearchTree sut = new BinarySearchTree();

  @Before
  public void createTree() {
    sut.add(55);
    sut.add(35);
    sut.add(65);
    sut.add(75);
    sut.add(25);
    sut.add(15);
    sut.add(45);
    sut.add(87);
    sut.add(98);
    sut.add(40);
    sut.add(42);
  }

  @Test
  public void add_addsNodesToTheTree() {
    BinarySearchTree<Integer> tree = new BinarySearchTree<>();
    tree.add(12);
    tree.add(3);
    tree.add(9);
    tree.add(8);
    tree.add(11);
    tree.add(1);
    tree.add(2);
    Assert.assertEquals("->1->2->3->8->9->11->12", tree.toStringInorder());
  }

  @Test
  public void toStringInorder_printsTreeElementsInInorder() {
    Assert.assertEquals("->15->25->35->40->42->45->55->65->75->87->98", sut.toStringInorder());
  }

  @Test
  public void toStringPreorder_printsTreeElementsPreorder() {
    Assert.assertEquals("->55->35->25->15->45->40->42->65->75->87->98", sut.toStringPreorder());
  }

  @Test
  public void toStringPostorder_printsTreeElementsPostorder() {
    Assert.assertEquals("->15->25->45->42->40->35->65->75->87->98->55", sut.toStringPostorder());
  }

  @Test
  public void toStringBFS_printsTreeElementsInBFSOrder() {
    Assert.assertEquals("->55->35->65->25->45->75->15->40->87->42->98", sut.toStringBFS());
  }

  @Test
  public void toStringDFS_printsTreeElementsInDFSOrder() {
    Assert.assertEquals("->55->35->25->15->45->40->42->65->75->87->98", sut.toStringDFS());
  }

  @Test
  public void toStringLeaves_PrintsTheTreeLeaves() {
    Assert.assertEquals("->15->42->98", sut.toStringLeaves());
  }

  @Test
  public void inOrderSuccessor_findsTheInOrderSuccessor() {
    Assert.assertEquals(65, sut.getInorderSuccessor(55));
    Assert.assertEquals(55, sut.getInorderSuccessor(45));
    Assert.assertEquals(40, sut.getInorderSuccessor(35));
  }
}
