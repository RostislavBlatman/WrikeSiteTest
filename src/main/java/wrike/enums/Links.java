package wrike.enums;

public enum Links {



        WRIKE_HOME_PAGE("https://www.wrike.com"),

        EMAIL_POSTFIX("+wpt@wriketask.qaa"),

        WRIKE_RESEND_PAGE("https://www.wrike.com/resend/"),

        WRIKE_IN_TWITTER("https://twitter.com/wrike"),

        TWITTER_ICON("/content/themes/wrike/dist/img/sprite/vector/footer-icons.symbol.svg?v1#twitter");

        public String text;

        Links(String text) {
            this.text = text;
        }



}
