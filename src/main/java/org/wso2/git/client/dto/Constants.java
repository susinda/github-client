package org.wso2.git.client.dto;


public final class Constants {

    public static final class ContentType {
        private ContentType() {
            throw new AssertionError();
        }

        public static final String APPLICATION_JSON = "application/json";
        public static final String APPLICATION_URL_ENCODED = "application/x-www-form-urlencoded";
    }

    public static final class Header {
        private Header() {
            throw new AssertionError();
        }

        public static final String AUTH = "Authorization";
        public static final String CONTENT_TYPE = "Content-Type";
    }

    public static final class HTTPStatus {
        private HTTPStatus() {
            throw new AssertionError();
        }

        public static final int OK = 200;
        public static final int CREATED = 201;
    }

    public static final String UTF_8 = "utf-8";
}
