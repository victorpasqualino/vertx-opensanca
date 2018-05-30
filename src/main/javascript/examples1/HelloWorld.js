
vertx.createHttpServer().requestHandler(function(request) {
    request.response().end("<body><h1>Hello from JavaScript Vert.X</h1></body>");
}).listen(8082);