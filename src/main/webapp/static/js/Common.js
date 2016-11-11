var Common = {
    /**
     * 格式化日期（不含时间）
     */
    formatterDate: function (date) {
        if (date == undefined) {
            return "";
        }
        /*json格式时间转js时间格式*/
        date = date.substr(1, date.length - 2);
        var obj = eval('(' + "{Date: new " + date + "}" + ')');
        var date = obj["Date"];
        if (date.getFullYear() < 1900) {
            return "";
        }
 
        var datetime = date.getFullYear()
                + "-"// "年"
                + ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) : "0"
                        + (date.getMonth() + 1))
                + "-"// "月"
                + (date.getDate() < 10 ? "0" + date.getDate() : date
                        .getDate());
        return datetime;
    },
    /**
     * 格式化日期（含时间"00:00:00"）
     */
    formatterDate2: function (date) {
        if (date == undefined) {
            return "";
        }
        /*json格式时间转js时间格式*/
        date = date.substr(1, date.length - 2);
        var obj = eval('(' + "{Date: new " + date + "}" + ')');
        var date = obj["Date"];
        if (date.getFullYear() < 1900) {
            return "";
        }
 
        /*把日期格式化*/
        var datetime = date.getFullYear()
                + "-"// "年"
                + ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) : "0"
                        + (date.getMonth() + 1))
                + "-"// "月"
                + (date.getDate() < 10 ? "0" + date.getDate() : date
                        .getDate()) + " " + "00:00:00";
        return datetime;
    },
    /**
     * 格式化去日期（含时间）
     */
    formatterDateTime: function (date) {
        if (date == undefined) {
            return "";
        }
        /*json格式时间转js时间格式*/
        date = date.substr(1, date.length - 2);
        var obj = eval('(' + "{Date: new " + date + "}" + ')');
        var date = obj["Date"];
        if (date.getFullYear() < 1900) {
            return "";
        }
 
        var datetime = date.getFullYear()
                + "-"// "年"
                + ((date.getMonth() + 1) > 10 ? (date.getMonth() + 1) : "0"
                        + (date.getMonth() + 1))
                + "-"// "月"
                + (date.getDate() < 10 ? "0" + date.getDate() : date
                        .getDate())
                + " "
                + (date.getHours() < 10 ? "0" + date.getHours() : date
                        .getHours())
                + ":"
                + (date.getMinutes() < 10 ? "0" + date.getMinutes() : date
                        .getMinutes())
                + ":"
                + (date.getSeconds() < 10 ? "0" + date.getSeconds() : date
                        .getSeconds());
        return datetime;
    }
};