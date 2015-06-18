package org.openmrs.module.reporting.xml.converter;

import com.thoughtworks.xstream.XStream;
import org.junit.Assert;
import org.junit.Test;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.report.util.ReportUtil;
import org.openmrs.module.reporting.xml.XmlReportUtil;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;

public class MapConverterTest {

    protected String getXml() {
        return ReportUtil.readStringFromResource("org/openmrs/module/reporting/xml/converter/map.xml");
    }

    @Test
    public void testMarshall() throws Exception {
        XStream xstream = XmlReportUtil.getXStream();
        xstream.alias("sample", Sample.class);
        Sample sample = (Sample)xstream.fromXML(getXml());
        Assert.assertThat(sample.parameters.size(), is(2));
        Assert.assertThat(sample.parameters.get("p1").getName(), is("startDate"));
        Assert.assertThat(sample.parameters.get("p2").getName(), is("endDate"));
    }

    class Sample {
        public Map<String, Parameter> parameters;
    }
}
