ZMUCA.newChallengeView = (function ($) {

	var renderTable = function(container,userModelArr,onUserClick){
		var table = ich.membersTable();
		$.each(userModelArr, function(ind, userModel) {
			var row = ich.membersTableRow(userModel);
			table.append(row)
			row.bind("tap",function(e){
				e.stopPropagation();
				e.preventDefault();
				onUserClick(e,userModel)
			})
			
		});
		container.html(table);
		table.listview()
	}
	var renderChallenges = function(container,challenges,onChallengeClick){
		var table = ich.membersTable();
		$.each(challenges,function(ind,val){
			var row = ich.listviewRow({
				name:val.name,
				image:val.image
			});
			row.bind("tap",function(e){
				e.stopPropagation();
				e.preventDefault();
				onChallengeClick(val)
			})
			table.append(row)
		})
		container.html(table);
		table.listview();
	}
	var renderModes =function(container,modes,onModeClick){
		$.each(modes,function(ind,mode){
			if(mode.id=="length"){
				var table = ich.membersTable();
				$.each(mode.params,function(ind,val){
					var row = ich.listviewRow({
						name:val.name,
						image:val.image
					});
					row.bind("tap",function(e){
						e.stopPropagation();
						e.preventDefault();
						onModeClick(val)
					})
					table.append(row)
				})
				container.html(table);
				table.listview();
			}
		})
	}
	var init = function () {
       
    };

    var pub = {
        init: init,
        renderTable:renderTable,
        renderChallenges:renderChallenges,
        renderModes:renderModes,
    };

    return pub;

} (jQuery));