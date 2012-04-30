var DisconnectionMonitor;
/**
 * Connection monitoring.
 */
DisconnectionMonitor = (function () {
	
	/**
	 * @param errorMessage Message displayed to the user when disconnected.
	 * @param timeout defines delay between two connection controls (in seconds).
	 */
    function DisconnectionMonitor(errorMessage, timeout) {
    	this.errorMessage = errorMessage || "";
        this.timeout = (timeout || 1) * 1000 * 60; // 1 minute by default
        this._cptr = 0;
    }
    
    /**
     * Checks if user is still connected by calling a dedicated jsp page, which can't be reached if 
     * user is disconnected.
     */
    DisconnectionMonitor.prototype.check = function () {
    	this._check(this);
    }
    
    /**
     * Private methode, please call check.
     * @param reference to the object itself for closure purpose.
     */
    DisconnectionMonitor.prototype._check = function (self) {
    	self = self || this;
   	
    	jQuery.ajax( {
			url :"jsp/admin/plugins/disconnection/ConnectionState.jsp",
			success : function(data) {
				if(data.isConnected) {
					setTimeout(function() { self._check(self); } , self.timeout);
				} else {
					self.disconnectionPopup();
					self.redirection();
				}
			},
    		error: function(xhr, ajaxOptions, thrownError) {
    			self.disconnectionPopup();
				self.redirection();
    		}
		});
    };
    
    /**
     * Displays alert message which warns user about his disconnection from the site.
     * 
     */
    DisconnectionMonitor.prototype.disconnectionPopup = function () {
        alert(this.errorMessage);
    };
    
    /**
     * Reload current page. Then, because user is disconnected, he is redirected to login admin page,
     * which take care to redirect user back to previous page.
     */
    DisconnectionMonitor.prototype.redirection = function () {
    	window.location.reload(true);	
    };
    return DisconnectionMonitor;
})();