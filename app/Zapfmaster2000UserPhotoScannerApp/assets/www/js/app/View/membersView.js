ZMUPSA.membersView = (function ($) {
	var renderMembersTable = function(userModelArr){
		var table = $(ich.membersTable()).appendTo("#ZMUPSA-members [data-role=content]")
		$.each(userModelArr,function(ind,val){
			var row = ich.membersTableRow(val)
			table.append(row);
		})
	}
	
	
	var init = function () {
       
    };

    var pub = {
        init: init,
        renderMembersTable:renderMembersTable
    };

    return pub;

} (jQuery));