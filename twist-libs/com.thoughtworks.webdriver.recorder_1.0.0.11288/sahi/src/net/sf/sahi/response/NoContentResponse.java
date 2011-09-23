package net.sf.sahi.response;

/**
 * Sahi - Web Automation and Test Tool
 * 
 * Copyright  2006  V Narayan Raman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


public class NoContentResponse extends HttpResponse {

    public NoContentResponse() {
        setFirstLine("HTTP/1.0 204 No Content");
        setHeader("Cache-Control", "no-cache");
        setHeader("Pragma", "no-cache");
        setHeader("Expires", "0");
        setData(new byte[0]);
        setRawHeaders(getRebuiltHeaderBytes());
    }
}
