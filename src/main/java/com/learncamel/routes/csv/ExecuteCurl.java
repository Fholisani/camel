package com.learncamel.routes.csv;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.exec.ExecBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExecuteCurl extends RouteBuilder {
    public void configure() throws Exception {
        List<String> args = new ArrayList<>();
        //args.add("curl");
        //args.add("-X");
        args.add("-c");
        args.add("-v");
        args.add("--location");
        args.add("accept: application/json");
        args.add("GET");
        args.add("https://khuthadzo-kg.co.za:8432/blogger/blog?pageNo=0&pageSize=10&sortBy=date");


//        from("direct:exec")
//                .setHeader(ExecBinding.EXEC_COMMAND_ARGS, constant(args))
//                .to("exec:/bin/sh")
//                .to("log:?level=INFO&showBody=true&showHeaders=true")
//                .convertBodyTo(String.class)
//                        .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        String wordCountOutput = exchange.getIn().getBody(String.class);
//                    }
//                })
//                .log("Executed OS cmd and received: ${body}")
//        ;

//        List<String> args = new ArrayList<>();
//        args.add("-X");
//        args.add("GET");
//        args.add("-H");
//        args.add("accept: application/json");
       // args.add("curl -X GET \"https://khuthadzo-kg.co.za:8432/blogger/blog?pageNo=0&pageSize=10&sortBy=date\" -H \"accept: application/json\"");

//        from("direct:exec")
//                .setHeader(ExecBinding.EXEC_COMMAND_ARGS, constant(args))
//                .to("exec:/bin/sh");


//        from("direct:exec")
//                .setHeader(ExecBinding.EXEC_COMMAND_ARGS, constant(args))
//                .recipientList(simple("exec:curl?args=https://khuthadzo-kg.co.za:8432/blogger/blog"))
//                .convertBodyTo(String.class)
//                .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        String wordCountOutput = exchange.getIn().getBody(String.class);
//                    }
//                })
//                .log("Executed OS cmd and received: ${body}");

        from("direct:exec")
               .setHeader(ExecBinding.EXEC_COMMAND_ARGS, constant(args))
                .to("exec:curl")
                .to("log:?level=INFO&showBody=true&showHeaders=true")
              //  .to("exec:curl?args=https://khuthadzo-kg.co.za:8432/blogger/blog")
                .log("Received Message is ${body} ")
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        // By default, the body is ExecResult instance
                        //assertIsInstanceOf(ExecResult.class, exchange.getIn().getBody());
                        // Use the Camel Exec String type converter to convert the ExecResult to String
                        // In this case, the stdout is considered as output
                        String wordCountOutput = exchange.getIn().getBody(String.class);

                        // do something with the word count
                        System.out.println(wordCountOutput);
                    }
                })

                .log("Process done.");


//        from("direct:exec")
//                .to("exec:curl?args=https://khuthadzo-kg.co.za:8432/blogger/blog")
//                .process(new Processor() {
//                    public void process(Exchange exchange) throws Exception {
//                        InputStream outFile = exchange.getIn().getBody(InputStream.class);
//                       // assertIsInstanceOf(InputStream.class, outFile);
//                        // do something with the out file here
//                    }
//                })
//                .to("direct:output");;

//        from("direct:start")
//                .log(LoggingLevel.DEBUG, "Enter into SCRIPT:")
//                .to("exec:bash?args=execute.sh")
//                .log(LoggingLevel.DEBUG, "End of Script:")
//                .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        String wordCountOutput = exchange.getIn().getBody(String.class);
//                    }
//                })
//                .log("Process done.");
    }
}
