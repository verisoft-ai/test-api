[![Maven Central](https://maven-badges.herokuapp.com/maven-central/co.verisoft/test.api/badge.svg)](https://maven-badges.herokuapp.com/maven-central/co.verisoft/test-api)

# What is Verisoft's test-api?
It is a collection of utilities, to help test automation 
engineers avoid writing the same code over and over again. 
It is based on Junit 5 unit test framework.

## How to install
You can add test-api to your maven pom.xml file using: <br>

    <dependency>
    <groupId>co.verisoft</groupId>
    <artifactId>test-api</artifactId>
    <version>0.0.5</version>
    </dependency>

## Features
Here are the list of features test-api currently has. 
You can see usage examples in the unit test area. Each of the 
features has unit test with simple explanations on how to 
use.

### Store
The store object is a thread safe mechanism for shared memory.
It assists the developer in passing objects between classes,
without coupling or building any connections between them.
For instance, you can put an object in store in a Junit 5 
extension and use it in your tests or other parts of your code.

### Extent Report Mechanism
A simple and easy way to add an extent report object to your 
code, without having to write any initialization / evaluation
or packing code. You just add an extension, and all is done 
for you.

### Extended Logging
Add logging mechanism for test life-cycle. 

### Jira x-ray plugin
The Jira x-ray features creates a JSON object during the test
process, which contains the test information

### Report Observer
There are so many places to report to - logs, functional report,
internal report server, external report server. Instead of writing
each report seperately, the report observer writes a report once
and each report client easily impelments its own integration.

##### How to use?
1. During tests, page objects or any other class, whenever you need to write a report, use the `Report`
object. For example:<br>
`Report.report(ReportSource.REPORT, ReportLevel.TRACE, "This is a report message");`<br>
or simply `Report.report("This is a report message");`
2. Implement an observer class, and use the `update` method to decide what to
do when the `Report` object is written and published:<br>


    public class SampleObserver extends BaseObserver {

        public SampleObserver() {
            super();
        }


        @Override
        public void update(ReportEntry reportEntry) {
            System.out.println("SampleObserver data is " + reportEntry.toString());
        }
    }

3. In this basic example, `Report.report("This is a report message");` will be
to the console

