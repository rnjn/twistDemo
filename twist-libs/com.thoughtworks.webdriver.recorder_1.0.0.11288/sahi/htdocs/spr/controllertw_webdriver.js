/**
Copyright 2011 ThoughtWorks Studios
 */
setCurrentAccessor = function(acc) {
	_c.currentAccessor = acc;
}

Controller.prototype.getAccessor = function() {
	this.sendMessageToBrowser("_sahi.currentlySelectedAccessor('" + document.getElementById("accessor").value + "')");
	return "_" + this.currentAccessor;
};

Controller.prototype.getAccessorText = function (accessor) {
  var matches = /(By\.[A-Za-z]*)\((\".*[^\\]\")\)/.exec(accessor);
  return matches[1] + "("  + matches[2] + ")";
};

Controller.prototype.getSelectedAssertion = function(){
	var value = $('aValue').value;
	var accessor = $('accessor').value;
	var s = $('assertions').value;
	var winName = $("windowName").value;
	accessor = this.makeJavaAccessor(accessor, winName);
	s = this.parseJavaAccessor(s, accessor, value);
	return s;
};

Controller.prototype.parseJavaAccessor = function(s, accessor, value) {
  if (s.indexOf("<accessor.By>") > -1) {
     var accessorText = this.getAccessorText(accessor);
     if (accessor.indexOf("findElements") > -1) {
	    var indexMatches = /\.get\(([^\)]*)\)$/.exec(accessor);
        return s.replace(/<accessor.By>/g, "browser," + accessorText + "," + indexMatches[1]).replace(/<value>/g, this.quote(value));
  	 }
     return s.replace(/<accessor.By>/g, "browser," + accessorText).replace(/<value>/g, this.quote(value));
  }
  return s.replace(/<accessor>/g, accessor).replace(/<value>/g, this.quote(value));
};

Controller.prototype.displayInfo = function(alternatives, accessor, value, windowName, assertions){
    $("accessor").value = alternatives[0] || "";
	this.populateOptions($("alternatives"), alternatives);
	$("aValue").value = (value) ? value : ""; 
	$("windowName").value = (windowName) ? windowName : ""; 
	this.populateOptions($("assertions"), assertions, "", "-- Choose Assertion --");
};
displayInfo = _c.wrap(_c.displayInfo);

setElementStates = function() {
	// disabling unwanted buttons on the accessor spy.
	document.getElementById("accessor").disabled=true;
	document.getElementById("rec_on").style.display='none';
	document.getElementById("rec_clear").style.display='none';
};

Controller.prototype.hover = function(){
	var s = "_mouseOver(" + this.getAccessor() + ")";
	this.sendMessageToBrowser(s);

	// explicit recording for mouse over has to be done, as the manual mouse over(intended to get the accessor in the accessor spy) should not be recorded.
	var accessor = $('accessor').value;
	this.recordStep('new org.openqa.selenium.interactions.Actions(browser).moveToElement(browser.' + accessor + ').build().perform();');
};
