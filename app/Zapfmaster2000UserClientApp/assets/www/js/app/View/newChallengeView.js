ZMUCA.newUserView = (function ($) {

	var renderTable = function(container,userModelArr){
		var table = ich.membersTable();
		$.each(userModelArr, function(ind, userModel) {
			table.append(ich.membersTableRow(userModel))
		});
		$(container).html(table);
		table.listview()
	}
	
	var init = function () {
       
    };

    var pub = {
        init: init,
        renderTable:renderTable
    };

    return pub;

} (jQuery));