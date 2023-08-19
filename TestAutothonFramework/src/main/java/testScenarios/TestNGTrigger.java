package testScenarios;

import java.util.Arrays;
import java.util.Collections;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class TestNGTrigger {

    public static void main(String[] args) {
         
         
         XmlSuite suite = new XmlSuite();
         suite.setName("SingleTestMethodSuite");

         // Create an XmlTest
         XmlTest test = new XmlTest(suite);
         test.setName("SingleTestMethodTest");

         // Add the class and method to the test
         XmlClass xmlClass = new XmlClass(TestCase_YoutubeDemo.class.getName());
         XmlInclude includeMethod1 = new XmlInclude("TC01_Youtube_Video_Quality_Change");
         xmlClass.getIncludedMethods().add(includeMethod1);
         test.addParameter("url", args[0]);
         test.getClasses().add(xmlClass);

         // Add the suite to TestNG
         TestNG testNG = new TestNG();
         testNG.setXmlSuites(Collections.singletonList(suite));

         // Run the tests
         testNG.run();
    }
}
