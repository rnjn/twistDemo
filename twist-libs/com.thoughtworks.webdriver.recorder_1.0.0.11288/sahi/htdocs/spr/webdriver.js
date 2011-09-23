/**
Copyright 2011 ThoughtWorks Studios
 */

//concat.js - start

DRIVER = "browser";

Sahi.prototype.alertMock = function (s) {
    if (this.isRecording()) {
        this.recordStep(this.getExpectAlertScript(s));
    }
    return this._alert(s);
};

Sahi.prototype.confirmMock = function (s) {
    var retVal = this.callFunction(this.real_confirm, window, s);
    if (this.isRecording()){
        this.recordStep(this.getExpectConfirmScript(s, retVal));
    }
    return retVal;
};

Sahi.prototype.oldBuildAccessorInfo = Sahi.prototype.buildAccessorInfo;

Sahi.prototype.buildAccessorInfo = function(el, acc, identifier, relationStr) {
    accessorInfo = this.oldBuildAccessorInfo(el, acc, identifier, relationStr);
    accessorInfo["el"] = el;
    return accessorInfo;
};

Sahi.prototype.promptMock = function (s) {
    var retVal = this.callFunction(this.real_prompt, window, s);
    if (this.isRecording()) {
      this.recordStep(this.getExpectPromptScript(s, retVal));
    }
    return retVal;
};

Sahi.prototype.oldPrepareADs = Sahi.prototype.prepareADs;

Sahi.prototype.prepareADs = function() {
    this.oldPrepareADs();
    this.addAD({tag: "CANVAS", type: null, event:"click", name: "_canvas", attributes: ["sahiText", "id", "className", "index"], action: "_click", value: "sahiText"});
};

_sahi.mockDialogs(window);

//concat.js - end


// WebDrver accessor transformers - start
function ByIdAccessor() {
   this.isApplicable = function(elInfo) {
      var elementId = elInfo.el.id;
      return ((elementId != null) && (elementId != ""));
   }
   this.toWebDriverAccessor = function(elInfo) {
      var elementId = elInfo.el.id;
      return _sahi.replaceLeadingUnderscore("findElement(By.id(\"" + elementId + "\"))");
   }
}

function ByNameAccessor() {
   this.isApplicable = function(elInfo) {
      var elementName = elInfo.el.getAttribute("name");
      return ((elementName != null) && (elementName != ""));
   }
   this.toWebDriverAccessor = function(elInfo) {
      var elementName = elInfo.el.getAttribute("name");
      return _sahi.replaceLeadingUnderscore("findElement(By.name(\"" + elementName + "\"))");
   }
}

function ByNameRadioAccessor() {
   this.isApplicable = function(elInfo) {
      return elInfo.type == "_radio" && elInfo.value != null;
   }
   this.toWebDriverAccessor = function(elInfo) {
      var radioElement = elInfo.el; 
      var elementName = radioElement.getAttribute("name");
      var nameIndex = (new IndexFinder().findElementIndex(documentBody(radioElement), radioElement, 0, new NameComparator(radioElement)) - 1);
      return _sahi.replaceLeadingUnderscore("findElements(By.name(\"" + elementName + "\")).get(" + nameIndex + ")");
   }
}

function ByLinkTextAccessor() {
   this.isApplicable = function(elInfo) {
      return elInfo.type == "_link" && elInfo.el.textContent != "";
   }
   this.toWebDriverAccessor = function(elInfo) {
      var elementText = elInfo.el.textContent;
      elementText = applyStyle(_sahi, elInfo.el, elementText);
      var linkIndex = (new IndexFinder().findElementIndex(documentBody(elInfo.el), elInfo.el, 0, new LinkTextComparator(_sahi, elInfo.el)) - 1 );
      return _sahi.replaceLeadingUnderscore("findElements(By.linkText(\"" + trimSpaces(elementText) + "\")).get(" + linkIndex + ")");    
   }
}

function ByClassNameAccessor() {
   this.isApplicable = function(elInfo) {
      return elInfo.el.className != null && elInfo.el.className != "";
   }
   this.toWebDriverAccessor = function(elInfo) {
      var classIndex = (new IndexFinder().findElementIndex(documentBody(elInfo.el), elInfo.el, 0, new ClassNameComparator(elInfo.el)) - 1 );
      return _sahi.replaceLeadingUnderscore("findElements(By.className(" + _sahi.quotedEscapeValue(elInfo.el.className.split(" ")[0]) + ")).get(" + classIndex + ")");
   }
}

function ByTagNameAccessor() {
   this.isApplicable = function(elInfo) {
      return true;
   }
   this.toWebDriverAccessor = function(elInfo) {
      var tagElement = elInfo.el;
      var tagIndex = (new IndexFinder().findElementIndex(documentBody(tagElement), tagElement, 0, new TagComparator(tagElement)) - 1 );
	  return _sahi.replaceLeadingUnderscore("findElements(By.tagName(" + _sahi.quotedEscapeValue(tagElement.tagName) + ")).get(" + tagIndex + ")");
   }
}

Sahi.prototype.webDriverElementAccessors = [ new ByIdAccessor(), new ByNameRadioAccessor(), new ByNameAccessor(), new ByLinkTextAccessor(), new ByClassNameAccessor(), new ByTagNameAccessor()];

// WebDrver accessor transformers - end

//web driver assertions - start
function ExistsAssertion() {
   this.isApplicable = function(sahiAssertion, elementInfo) {
     return (sahiAssertion.indexOf("<accessor>.exists()") > -1);
   }
   this.toWebdriverAssertion = function(sahiAssertion) {
      return sahiAssertion.replace("<accessor>.exists()", "com.thoughtworks.webdriver.Utils.exists(<accessor.By>)");
   }     
}
function VisibleAssertion() {
   this.isApplicable = function(sahiAssertion, elementInfo) {
     return (sahiAssertion.indexOf("<accessor>.isVisible()") > -1);
   }
   this.toWebdriverAssertion = function(sahiAssertion) {
      return sahiAssertion.replace("<accessor>.isVisible()", "<accessor>.isDisplayed()");
   }    
}
function ValueAssertion() {
   this.isApplicable = function(sahiAssertion, elementInfo) {
     return sahiAssertion.indexOf("<accessor>.value()") > -1;
   }
   this.toWebdriverAssertion = function(sahiAssertion) {
     return sahiAssertion.replace("<accessor>.value()", "<accessor>.getText()");
   }           
}
function TextAssertion() {
   this.isApplicable = function(sahiAssertion, elementInfo) {
     return (sahiAssertion.indexOf("<accessor>.text()") > -1);
   }
   this.toWebdriverAssertion = function(sahiAssertion) {
     return sahiAssertion.replace("<accessor>.text()", "<accessor>.getText()");
   }
}
function SelectedTextAssertion() {
   this.isApplicable = function(sahiAssertion, elementInfo) {
     return (sahiAssertion.indexOf("<accessor>.selectedText()") > -1);
   }
   this.toWebdriverAssertion = function(sahiAssertion) {
     return sahiAssertion.replace("<accessor>.selectedText()", "new org.openqa.selenium.support.ui.Select(<accessor>).getFirstSelectedOption().getText()");
   }    
}
function CheckedAssertion() {
   this.isApplicable = function(sahiAssertion, elementInfo) {
     return (sahiAssertion.indexOf("<accessor>.checked()") > -1);
   }
   this.toWebdriverAssertion = function(sahiAssertion) {
     return sahiAssertion.replace("<accessor>.checked()", "<accessor>.isSelected()");
   }
}
function AttributeValueAssertion() {
   this.isApplicable = function(sahiAssertion, elementInfo) {
     var appliesToElement = (elementInfo.type == "_textbox") || (elementInfo.type == "_textarea");
     if (appliesToElement === true) {
        return (sahiAssertion.indexOf("<accessor>.value()") > -1) 
          || (sahiAssertion.indexOf("<accessor>.text()") > -1)
          || (sahiAssertion.indexOf("<accessor>.selectedText()") > -1);
     }
     return false;
   }
   this.toWebdriverAssertion = function(sahiAssertion) {
     var attrAcc = "<accessor>.getAttribute(\"value\")";
     return sahiAssertion.replace("<accessor>.value()", attrAcc).replace("<accessor>.text()", attrAcc).replace("<accessor>.selectedText()", attrAcc);
   }
}

Sahi.prototype.webdriverAssertions = [ new ExistsAssertion(), new VisibleAssertion(), new ValueAssertion(), new TextAssertion(), new SelectedTextAssertion(), new CheckedAssertion(), new AttributeValueAssertion()];

//web driver assertions -end

//language_pack.js - start

if (_sahi.controllerMode == "java"){
    _sahi.popupIndicated = false;
    
    Sahi.prototype.getExpectPromptScript = function(s, retVal) {
      var decision = "dismiss()";
      var promptValue = "";
      if (retVal) {
        promptValue = addWebdriverPopupDomainPrefixes(this, DRIVER + ".switchTo().alert().sendKeys(\"" + retVal + "\"\);\n");
        decision = "accept()";
      }
      return promptValue + addWebdriverPopupDomainPrefixes(this, DRIVER + ".switchTo().alert()." + decision + ";");
    };
    
    Sahi.prototype.getExpectConfirmScript = function(s, retVal) {
      var decision = "accept()";
      if (!retVal) {
        decision = "dismiss()";
      }
	  return addWebdriverPopupDomainPrefixes(this, DRIVER + ".switchTo().alert()." + decision);
    };
    
    Sahi.prototype.getExpectAlertScript = function(s) {
      return addWebdriverPopupDomainPrefixes(this, DRIVER + ".switchTo().alert().accept();");
    };
    
    Sahi.prototype.getNavigateToScript = function(url) {
      return DRIVER + "." + this.getPopupDomainPrefixes() + "get(" + this.quotedEscapeValue(url) + ");";
    };
    
    Sahi.prototype.getScript = function (infoAr) {
        var info = infoAr[0];
        var i = 0;
        var accessor = this.escapeDollar(this.getWebDriverAccessor(info));
        if (accessor == null) {
          return "";
        }
        if (accessor.indexOf("_") == 0) {
          accessor = accessor.substring(1);
        }
        var ev = info.event;
        var value = info.value;
        var type = info.type;
        var popup = this.getPopupName();
        var cmd = null;
        cmd = resolveEvent(info, _sahi).performOn(accessor, resolveElement(info));
	if (cmd == "") {
	   return "";
	}	
        cmd = addWebdriverPopupDomainPrefixes(this, cmd);
        return cmd;
	 };
	 
	 Sahi.prototype.replaceLeadingUnderscore = function(accessor) {
	    var modifiedAcc = (accessor.indexOf("_") == 0) ? accessor.substring(1) : accessor;
	    return this.escapeDollar(modifiedAcc);
	 };
	 
	 Sahi.prototype.getAvailableAccessors = function(info) {
	 	if (info == null) return "";
		var accessors = [];
		for (var idx=0; idx < this.webDriverElementAccessors.length; idx++) {
		  if (this.webDriverElementAccessors[idx].isApplicable(info)) {
		    accessors.push(this.webDriverElementAccessors[idx].toWebDriverAccessor(info));
		  }
		}
		return accessors;
	 };
	 
    Sahi.prototype.getWebDriverAccessor = function (info) {
		var accessors = this.getAvailableAccessors(info);
		var accessorToRecord;
 		if (this.selectedAccessor && this.selectedAccessor != "" && this.isSelectedAccessorIn(accessors)) {
			accessorToRecord = this.selectedAccessor;
		} else {
			accessorToRecord = accessors[0];
		}
		this.selectedAccessor = ""; //clear selected accessor.
		return accessorToRecord;
    };


   Sahi.prototype.isSelectedAccessorIn = function(accessors) {
	 for (i in accessors) {
	   if (accessors[i] == this.selectedAccessor) {
	      return true;
	   }
	 }
	 return false;
   };
     
    Sahi.prototype.identifyAndDisplay = function(el) {
      var elInfo = this.identify(this.getKnownTags(el));
      if (elInfo == null || elInfo.apis == null) return;
      var acc = null;
      var accessors = [];
      if (elInfo.apis.length > 0) {
         acc = elInfo.apis[0];
         controller_win = this.getController();
         if (controller_win && !controller_win.closed) {
            controller_win.setCurrentAccessor(this.getAccessor1(acc));
         }
         accessors = this.getAvailableAccessors(elInfo.apis[0]);
         var driverAssertions = this.getWebDriverAssertions(elInfo.assertions, elInfo.apis[0]);
         this.sendIdentifierInfo(accessors,
           this.getAccessor1(acc),
           this.escapeValue(acc.value),
           this.getPopupDomainPrefixes(el),
           driverAssertions);
      }
    };

    Sahi.prototype.currentlySelectedAccessor = function(accessor) {
	  this.selectedAccessor = accessor;
    }

    Sahi.prototype.sendIdentifierInfo = function(accessors, escapedAccessor, escapedValue, popupName, assertions){
    	var controlWin = this.getController();
    	if (controlWin && !controlWin.closed) {
    	  controlWin.displayInfo(accessors, escapedAccessor, escapedValue, popupName, assertions);
    	}
    }
    
    Sahi.prototype.getWebDriverAssertions = function(sahiAssertions, elementInfo) {
      var applicableAssertions = [];
      for (var i=0; i < sahiAssertions.length; i++) {
         var assertion =  sahiAssertions[i];
         for (var idx=0; idx < this.webdriverAssertions.length; idx++) {
            var candidateAssertion = this.webdriverAssertions[idx];
            if (candidateAssertion.isApplicable(assertion, elementInfo)) {
               applicableAssertions.push(candidateAssertion.toWebdriverAssertion(assertion));
            }
         }   
      }
      if (sahiAssertions.length > 0) {
          applicableAssertions.push("assertTrue(<accessor>.isEnabled());");
      }
      return applicableAssertions;
    }
}

//language_pack.js - end

// Custom webdriver recorder scripts - start

function ClickEvent() {
	this.performOn = function (accessor, element) { return DRIVER + "." + accessor + "." + element.click(); };
}

function SelectEvent(quotedValue) {
	this.value = quotedValue;
	this.performOn = function (accessor, element) { return "new org.openqa.selenium.support.ui.Select(" + DRIVER + "." + accessor + ")" + "." + "selectByVisibleText(" + this.value + ")" };
}

function SendKeysEvent(quotedValue) {
	this.value = quotedValue;
	this.performOn = function (accessor, element) { return DRIVER + "." + accessor + "." + element.sendKeys(this.value); };
}

function CheckBoxElement() {
	this.click = function () { return 'click()' };
	this.sendKeys = function (quotedValue) {};
}

function RadioElement() {
	this.click = function () { return 'click()' };
	this.sendKeys = function (quotedValue) {};
}

function SubmitButton() {
	this.click = function () { return 'click()' };
	this.sendKeys = function (quotedValue) {};
}


function OtherElement() {
	this.click = function () { return 'click()' };
	this.sendKeys = function (quotedValue) { return 'sendKeys(' + quotedValue + ')' };
}

resolveElement = function (info) {
	if (info.type == '_checkbox') {
		return new CheckBoxElement();
	} else if (info.type == '_radio') {
		return new RadioElement();
	} else if (info.type == '_submit') {
		return new SubmitButton();
	} else {
		return new OtherElement();
	} 
}

resolveEvent = function (info, _sahi) {
	var event = info.event;
	if (event == "_click") {
		return new ClickEvent();
	} else if (event == "_setValue" || event == "_setFile") {
		return new SendKeysEvent(getValueForElement(info, _sahi));
	} else if (event == "_setSelected") {
		return new SelectEvent(getValueForElement(info, _sahi));
	}
}

getValueForElement  = function (info, _sahi) {
	var value = info.value;
	if (value == null) {
		value = "";
	}
	return _sahi.quotedEscapeValue(value);
}

function IndexFinder() {
	this.found = false;
	this.findElementIndex = 
      function( object, elementToFind, index, elementComparator) {
        if ( elementComparator.hasSameAttributeAs(object)) {
          index++;
          if (elementToFind == object) {
            this.found = true;
            return index;
          }
        }
        
        for ( var i = 0; i < object.childNodes.length; i++ ) {
          if (this.found) {
            break;
          }
          index += this.findElementIndex( object.childNodes[i], elementToFind, 0, elementComparator);
        }
        return index;
      };
}

function LinkTextComparator(sahi, element) {
	this.element = element;
	this.sahi = sahi;
	this.hasSameAttributeAs = 
	  function (otherElement) {
	    return (this.element.tagName == otherElement.tagName) && (applyStyle(this.sahi, this.element, this.element.textContent) == applyStyle(this.sahi, otherElement, otherElement.textContent));
	  };
}

function ClassNameComparator(element) {
	this.element = element;
	this.hasSameAttributeAs = function (otherElement) {
				var otherClassname = otherElement.className;
				if (otherClassname != null && otherClassname != "") {
					var otherClasses = trimSpaces(otherClassname).split(" ");
					var elementClass = trimSpaces(this.element.className).split(" ")[0];
					return this.arrayContains(otherClasses, elementClass);
				} else {
					return false;
				}
			}
	this.arrayContains = function(array, string) {
		for (var i=0; i < array.length; i++) {
			if(array[i] == string ) {
				return true;
			}
		}
	}
}

function TagComparator(element) {
	this.element = element;
	this.hasSameAttributeAs = function (otherElement) {
				return this.element.tagName == otherElement.tagName;
			}
}

function NameComparator(element) {
	this.element = element;
	this.hasSameAttributeAs = function (otherElement) {
				return this.element.tagName == otherElement.tagName && this.element.getAttribute("name") == otherElement.getAttribute("name");
			}
}

addWebdriverPopupDomainPrefixes = function(sahi, cmd) {
	var popupIndicator = "";
	if (sahi.isPopup()) {
		//var popupName = sahi.getPopupName();
		//return "Iterator<String> windowIterator = browser.getWindowHandles();\n" +
		//		"while(windowIterator.hasNext()) { \n" +
		//			"String windowHandle = windowIterator.next();\n" +
		//			"if (browser.switchTo(windowHandle).getTitle().equals(\"" + popupName + "\") { \n" +
		//				"browser.switchTo(windowHandle)." + cmd + ";\n" +
		//				"break;" +
		//			"}" +
		//		"}";
		if (!sahi.popupIndicated) {
			popupIndicator = "//Write your logic to locate the appropriate popup before using commented actions.\n" +
					"//Look at - 'How do I handle popup windows in Selenium 2' in Twist Help for more details.\n"
			sahi.popupIndicated = true;
		}
		return popupIndicator + "//action in popup \"" + sahi.getPopupName() +"\". " + cmd.replace(DRIVER, "popup");
	} else {
		return cmd;
	}
};

documentBody = function(element) {
	while(element.parentNode != null) {
		element = element.parentNode;
	}
	return element;
};



trimSpaces = function (stringWithSpaces) {
	return stringWithSpaces.replace(/\n+/g, " ").replace(/\t+/g, " ").replace(/^\s+|\s+$/g,"").replace(/\s+/g," ");
};

applyStyle = function(sahi, ele, elText) {
    var textTransform = sahi._style(ele, "text-transform");
    if (textTransform) {
      if (textTransform == 'uppercase') {
        elText = elText.toUpperCase();
      } else if (textTransform == 'lowercase') {
        elText = elText.toLowerCase();
      } else if (textTransform == 'capitalize') {
        elText = this.toCamelCase(elText);
      }
    }
    return elText;
};

// Custom webdriver recorder scripts - start
