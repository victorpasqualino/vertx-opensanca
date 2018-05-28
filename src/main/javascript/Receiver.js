
vertx.createHttpServer(config()).requestHandler(function(req) {
    console.log(context.deploymentID());
    req.response().end("<body><h1>Hello from JavaScript Vert.X</h1></body>");
}).listen();