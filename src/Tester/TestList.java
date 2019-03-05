package Tester;

import main.CompareFactory;
import org.junit.Test;

public class TestList {
    @Test
    public void Facotory_test()
    {
        CompareFactory.init();
        CompareFactory.createDiagram=false;
        CompareFactory.test_compare2();
    }
}
