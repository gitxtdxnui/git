$.extend($.fn.validatebox.defaults.rules, {  
    /*必须和某个字段相等*/
    equalTo: {
        validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'字段不匹配'
    }
});
var UTILS = {
	searchGridData : function(formId, gridId) {
		$('#' + gridId).datagrid('load', this.serializeObject($('#' + formId)));
	},
	serializeObject : function(form) {
		var o = {};
		$.each(form.serializeArray(), function(index) {
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
			}
		});
		return o;
	},
	getFormatDateTime : function(time) {
		if(time){
			var date = new Date(time);
			var y = date.getFullYear();
			var m = date.getMonth() + 1;
			var d = date.getDate();
			var hour = date.getHours();
			var min = date.getMinutes();
			var sec = date.getSeconds();
			return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d)
					+ ' ' + (hour < 10 ? '0' + hour : hour) + ':'
					+ (min < 10 ? '0' + min : min) + ':'
					+ (sec < 10 ? '0' + sec : sec);
		}
		return null;
	}
}