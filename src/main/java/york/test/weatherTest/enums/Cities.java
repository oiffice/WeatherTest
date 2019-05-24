package york.test.weatherTest.enums;

public enum Cities {
        宜蘭縣(""),
        桃園市(""),
        新竹縣(""),
        新竹市(""),
        苗栗縣(""),
        彰化縣(""),
        南投縣(""),
        雲林縣(""),
        嘉義縣(""),
        嘉義市(""),
        屏東縣(""),
        臺東縣(""),
        花蓮縣(""),
        澎湖縣(""),
        基隆市(""),
        新北市("F-D0047-069"),
        臺北市("F-D0047-061"),
        台北市("F-D0047-061"),
        臺中市(""),
        台中市(""),
        臺南市(""),
        台南市(""),
        高雄市(""),
        金門市(""),
        金門縣(""),
        連江市("");


        private String requestCode;

        Cities(String requestCode) {
                this.requestCode = requestCode;
        }

        public String getRequestCode() {
                return this.requestCode;
        }
}
