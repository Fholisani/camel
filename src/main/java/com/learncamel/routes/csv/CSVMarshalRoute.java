package com.learncamel.routes.csv;

import com.learncamel.domain.Employee;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.exec.ExecBinding;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spi.DataFormat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dilip on 12/17/17.
 */
public class CSVMarshalRoute extends RouteBuilder {


    public void configure() throws Exception {

        DataFormat bindy = new BindyCsvDataFormat(Employee.class);
        List<String> args = new ArrayList<>();
        args.add("-X");
        args.add("-l");
        args.add("-m");
        args.add("-o");
        args.add("GET");
        args.add("https://khuthadzo-kg.co.za:8432/blogger/blog");
        from("direct:objInput")
                .marshal(bindy)
                .log("Marshaled Input : ${body} and headers are ${headers}")
                .to("file:data/csv/output?fileName=output.txt")
                .setHeader(ExecBinding.EXEC_COMMAND_ARGS, constant(args))
              //  .to("exec:curl?args=https://khuthadzo-kg.co.za:8432/blogger/blog")
                .log("Received Message is ${body} and Headers are ${headers}")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String wordCountOutput = exchange.getIn().getBody(String.class);
                    }
                })
                .log("Executed OS cmd and received: ${body}");

    }
}
