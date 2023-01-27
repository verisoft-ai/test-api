package co.verisoft.fw.report.observer;

public class SampleObserver extends BaseObserver {

    @Override
    public void update(ReportEntry reportEntry) {
        System.out.println("SampleObserver data is " + reportEntry.toString());
    }

    public SampleObserver() {
        super();
    }
}
