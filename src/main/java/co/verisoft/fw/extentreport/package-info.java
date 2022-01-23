/**
 * Handles ExtentReport mechanism within the framework. Junit 5 has a mechanism for passing and failing tests,
 * Logging system used is a seperate module and the report is a third module. The report handles both logging of events
 * and deciding of a test pass or fail. These modules needs to sync- write to log if written to report, pass or fail
 * sync between the report and Junit 5. This package implements this behavior.
 */
package co.verisoft.fw.extentreport;