/*
 * MIT License
 *
 * Copyright (c) 2023 Silvio Wangler
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.wangler.micronaut.smocker;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import java.util.Map;

/**
 *
 *
 * <pre>
 * [
 *   {
 *     "request":  {
 *       "method": "GET",
 *       "path": "/hello"
 *     },
 *     "response": {
 *       "status": 200,
 *       "headers": { "Content-Type": "text/plain"},
 *       "body": "Hello"
 *     }
 *   }
 * ]
 * </pre>
 */
@Serdeable
public record SmockerMock(Request request, Response response) {

  @Serdeable
  public record Request(String method, Map<String, String> headers, String path) {
    public Request(String method, Map<String, String> headers, String path) {
      this.method = method;
      this.headers = headers;
      this.path = path;
    }

    public Request(String method, String path) {
      this(method, Map.of("Accept", "application/json"), path);
    }
  }

  @Serdeable
  @Introspected
  public record Response(int status, Map<String, String> headers, String body) {

    public Response(int status, Map<String, String> headers, String body) {
      this.status = status;
      this.headers = headers;
      this.body = body;
    }

    public Response(int status, String body) {
      this(status, Map.of("Content-Type", "application/json"), body);
    }

    public Response(String body) {
      this(200, body);
    }
  }
}
