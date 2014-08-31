	function log(m){
		if(console)console.log(m);
	}
document.body.addEventListener('touchmove', function(e) {
  // This prevents native scrolling from happening.
  e.preventDefault();
}, false);
ZMO.Scroller = function(element) {
	  this.element = element;
	  this.startTouchY = 0;
	  this.animateTo(0);
	  
	  element.addEventListener("touchstart", this, false);
	  element.addEventListener("touchmove", this, false);
	  element.addEventListener("touchend", this, false);
	  element.addEventListener("webkitTransitionEnd", this, false);
	}

	ZMO.Scroller.prototype.handleEvent = function(e) {
	  switch (e.type) {
	    case "touchstart":
	      this.onTouchStart(e);
	      break;
	    case "touchmove":
	      this.onTouchMove(e);
	      break;
	    case "touchend":
	      this.onTouchEnd(e);
	      break;
	    case "webkitTransitionEnd":
		      this.onTransitionEnd(e);
		      break;
		      
	  }
	}

	ZMO.Scroller.prototype.onTouchStart = function(e) {
	  // This will be shown in part 4.
	  this.stopMomentum();
	  this.startTime = new Date().getTime();
	  this.startTouchY = e.touches[0].clientY;
	  this.contentStartOffsetY = this.contentOffsetY;
	}

	ZMO.Scroller.prototype.onTouchMove = function(e) {
	  if (this.isDragging(e.touches[0].clientY)) {
	    var currentY = e.touches[0].clientY;
	    var deltaY = currentY - this.startTouchY;
	    var newY = deltaY + this.contentStartOffsetY;
	    this.animateTo(newY);
	  }
	}

	ZMO.Scroller.prototype.onTouchEnd = function(e) {
		
	  if (Math.abs(this.contentStartOffsetY-this.contentOffsetY)>1) {
	    if (this.shouldStartMomentum()) {
	      // This will be shown in part 3.
	      this.doMomentum();
	    } else {
	      this.snapToBounds();
	    }
	  }
	};

	ZMO.Scroller.prototype.onTransitionEnd = function(e) {
		log("on transition end");
	  if (Math.abs(this.contentStartOffsetY-this.contentOffsetY)>1) {
	    if (this.shouldStartMomentum()) {
	      // This will be shown in part 3.
	      //this.doMomentum();
	    } else {
	      this.snapToBounds();
	    }
	  }
	};
	ZMO.Scroller.prototype.animateTo = function(offsetY) {
	  this.contentOffsetY = offsetY;

	  // We use webkit-transforms with translate3d because these animations
	  // will be hardware accelerated, and therefore significantly faster
	  // than changing the top value.
	  this.element.style.webkitTransform = "translate3d(0, " + offsetY + "px, 0)";
	};

	// Implementation of this method is left as an exercise for the reader.
	// You need to measure the current position of the scrollable content
	// relative to the frame. If the content is outside of the boundaries
	// then simply reposition it to be just within the appropriate boundary.
	ZMO.Scroller.prototype.snapToBounds = function() {
	 // ...
		log("Snap to bounds "+ this.contentOffsetY);
		if(this.contentOffsetY<=0){
			var newY = 0;
			this.animateTo(0);
		}else{
			this.animateTo(0);
		}
	};

	// Implementation of this method is left as an exercise for the reader.
	// You need to consider whether their touch has moved past a certain
	// threshold that should be considered ÔdraggingÕ.
	ZMO.Scroller.prototype.isDragging = function(touchY) {
		var threshold = 5;
		return ( Math.abs(touchY - this.startTouchY)>threshold);
		
	
	};

	// Implementation of this method is left as an exercise for the reader.
	// You need to consider the end velocity of the drag was past the
	// threshold required to initiate momentum.
	ZMO.Scroller.prototype.shouldStartMomentum = function() {
		log("Should start momemtun:" +this.getEndVelocity());
		var case1 =this.contentOffsetY<0;
		var case2 =this.element.offsetHeight >this.contentOffsetY;
	 	return case1||case2;
		
	};
	ZMO.Scroller.prototype.getEndVelocity = function() {
		return ( this.contentOffsetY-this.contentStartOffsetY )/(new Date().getTime()-this.startTime);
		
	}
	ZMO.Scroller.prototype.isDecelerating = function(e) {
		return true;
	}
	ZMO.Scroller.prototype.doMomentum = function() {
		  // Calculate the movement properties. Implement getEndVelocity using the
		  // start and end position / time.
		  var velocity = this.getEndVelocity();
		  var acceleration = velocity < 0 ? 0.0005 : -0.0005;
		  var displacement = - (velocity * velocity) / (2 * acceleration);
		  var time = - velocity / acceleration;

		  // Set up the transition and execute the transform. Once you implement this
		  // you will need to figure out an appropriate time to clear the transition
		  // so that it doesnÕt apply to subsequent scrolling.
		  this.element.style.webkitTransition = "-webkit-transform " + time +
		      "ms cubic-bezier(0.33, 0.66, 0.66, 1)";

		  var newY = this.contentOffsetY + displacement;
		  this.contentOffsetY = newY;
		  this.element.style.webkitTransform = "translate3d(0, " + newY + "px, 0)";
		  
		}
	
	ZMO.Scroller.prototype.stopMomentum = function() {
		  if (this.isDecelerating()) {
		    // Get the computed style object.
		    var style = document.defaultView.getComputedStyle(this.element, null);
		    // Computed the transform in a matrix object given the style.
		    var transform = new WebKitCSSMatrix(style.webkitTransform);
		    // Clear the active transition so it doesnÕt apply to our next transform.
		    this.element.style.webkitTransition = "";
		    // Set the element transform to where it is right now.
		    this.animateTo(transform.m42);
		  }
		}