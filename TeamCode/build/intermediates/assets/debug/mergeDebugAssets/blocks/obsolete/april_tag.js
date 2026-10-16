/**
 * @license
 * Copyright 2026 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @fileoverview FTC robot blocks related to AprilTag detection.
 * @author Liz Looney
 */

// The following are generated dynamically in HardwareUtil.fetchJavaScriptForHardware():
// aprilTagIdentifierForJavaScript
// The following are defined in vars.js:
// createNonEditableField
// builderColor
// functionColor
// getPropertyColor

Blockly.Blocks['aprilTagDetection_getProperty_Number'] = {
  init: function() {
    var PROPERTY_CHOICES = [
        ['id', 'id'],
        ['hamming', 'hamming'],
        ['decisionMargin', 'decisionMargin'],
        ['center.x', 'center.x'],
        ['center.y', 'center.y'],
        ['corners[0].x', 'corners[0].x'],
        ['corners[0].y', 'corners[0].y'],
        ['corners[1].x', 'corners[1].x'],
        ['corners[1].y', 'corners[1].y'],
        ['corners[2].x', 'corners[2].x'],
        ['corners[2].y', 'corners[2].y'],
        ['corners[3].x', 'corners[3].x'],
        ['corners[3].y', 'corners[3].y'],
        ['ftcPose.x', 'ftcPose.x'],
        ['ftcPose.y', 'ftcPose.y'],
        ['ftcPose.z', 'ftcPose.z'],
        ['ftcPose.yaw', 'ftcPose.yaw'],
        ['ftcPose.pitch', 'ftcPose.pitch'],
        ['ftcPose.roll', 'ftcPose.roll'],
        ['ftcPose.range', 'ftcPose.range'],
        ['ftcPose.bearing', 'ftcPose.bearing'],
        ['ftcPose.elevation', 'ftcPose.elevation'],
        ['rawPose.x', 'rawPose.x'],
        ['rawPose.y', 'rawPose.y'],
        ['rawPose.z', 'rawPose.z'],
        ['robotPose.position.x', 'robotPose.position.x'],
        ['robotPose.position.y', 'robotPose.position.y'],
        ['robotPose.position.z', 'robotPose.position.z'],
        ['robotPose.orientation.roll', 'robotPose.orientation.roll'],
        ['robotPose.orientation.pitch', 'robotPose.orientation.pitch'],
        ['robotPose.orientation.yaw', 'robotPose.orientation.yaw'],
    ];
    this.setOutput(true, 'Number');
    this.appendDummyInput()
        .appendField(createNonEditableField('AprilTagDetection'))
        .appendField('.')
        .appendField(new Blockly.FieldDropdown(PROPERTY_CHOICES), 'PROP');
    this.appendValueInput('APRIL_TAG_DETECTION').setCheck('AprilTagDetection')
        .appendField('aprilTagDetection')
        .setAlign(Blockly.ALIGN_RIGHT);
    this.setColour(getPropertyColor);
    // Assign 'this' to a variable for use in the closures below.
    var thisBlock = this;
    var TOOLTIPS = [
        ['id', 'Returns the id field of the AprilTagDetection.'],
        ['hamming', 'Returns the hamming field of the AprilTagDetection.'],
        ['decisionMargin', 'Returns the decisionMargin field of the AprilTagDetection.'],
        ['center.x', 'Returns the center.x field of the AprilTagDetection.'],
        ['center.y', 'Returns the center.y field of the AprilTagDetection.'],
        ['corners[0].x', 'Returns the corners[0].x field of the AprilTagDetection.'],
        ['corners[0].y', 'Returns the corners[0].y field of the AprilTagDetection.'],
        ['corners[1].x', 'Returns the corners[1].x field of the AprilTagDetection.'],
        ['corners[1].y', 'Returns the corners[1].y field of the AprilTagDetection.'],
        ['corners[2].x', 'Returns the corners[2].x field of the AprilTagDetection.'],
        ['corners[2].y', 'Returns the corners[2].y field of the AprilTagDetection.'],
        ['corners[3].x', 'Returns the corners[3].x field of the AprilTagDetection.'],
        ['corners[3].y', 'Returns the corners[3].y field of the AprilTagDetection.'],
        ['ftcPose.x', 'Returns the ftcPose.x field of the AprilTagDetection.'],
        ['ftcPose.y', 'Returns the ftcPose.y field of the AprilTagDetection.'],
        ['ftcPose.z', 'Returns the ftcPose.z field of the AprilTagDetection.'],
        ['ftcPose.yaw', 'Returns the ftcPose.yaw field of the AprilTagDetection.'],
        ['ftcPose.pitch', 'Returns the ftcPose.pitch field of the AprilTagDetection.'],
        ['ftcPose.roll', 'Returns the ftcPose.roll field of the AprilTagDetection.'],
        ['ftcPose.range', 'Returns the ftcPose.range field of the AprilTagDetection.'],
        ['ftcPose.bearing', 'Returns the ftcPose.bearing field of the AprilTagDetection.'],
        ['ftcPose.elevation', 'Returns the ftcPose.elevation field of the AprilTagDetection.'],
        ['rawPose.x', 'Returns the rawPose.x field of the AprilTagDetection.'],
        ['rawPose.y', 'Returns the rawPose.y field of the AprilTagDetection.'],
        ['rawPose.z', 'Returns the rawPose.z field of the AprilTagDetection.'],
        ['robotPose.position.x', 'Returns the robotPose.position.x field of the AprilTagDetection.'],
        ['robotPose.position.y', 'Returns the robotPose.position.y field of the AprilTagDetection.'],
        ['robotPose.position.z', 'Returns the robotPose.position.z field of the AprilTagDetection.'],
        ['robotPose.orientation.roll', 'Returns the robotPose.orientation.roll field of the AprilTagDetection.'],
        ['robotPose.orientation.pitch', 'Returns the robotPose.orientation.pitch field of the AprilTagDetection.'],
        ['robotPose.orientation.yaw', 'Returns the robotPose.orientation.yaw field of the AprilTagDetection.'],
    ];
    this.setTooltip(function() {
      var key = thisBlock.getFieldValue('PROP');
      for (var i = 0; i < TOOLTIPS.length; i++) {
        if (TOOLTIPS[i][0] == key) {
          return TOOLTIPS[i][1];
        }
      }
      return '';
    });
    this.getFtcJavaOutputType = function() {
      var property = thisBlock.getFieldValue('PROP');
      switch (property) {
        case 'id':
        case 'hamming':
          return 'int';
        case 'decisionMargin':
          return 'float';
        case 'center.x':
        case 'center.y':
        case 'corners[0].x':
        case 'corners[0].y':
        case 'corners[1].x':
        case 'corners[1].y':
        case 'corners[2].x':
        case 'corners[2].y':
        case 'corners[3].x':
        case 'corners[3].y':
        case 'ftcPose.x':
        case 'ftcPose.y':
        case 'ftcPose.z':
        case 'ftcPose.yaw':
        case 'ftcPose.pitch':
        case 'ftcPose.roll':
        case 'ftcPose.range':
        case 'ftcPose.bearing':
        case 'ftcPose.elevation':
        case 'rawPose.x':
        case 'rawPose.y':
        case 'rawPose.z':
        case 'robotPose.position.x':
        case 'robotPose.position.y':
        case 'robotPose.position.z':
        case 'robotPose.orientation.roll':
        case 'robotPose.orientation.pitch':
        case 'robotPose.orientation.yaw':
          return 'double';
        default:
          throw 'Unexpected property ' + property + ' (aprilTagDetection_getProperty_Number getOutputType).';
      }
    };
  }
};

Blockly.JavaScript['aprilTagDetection_getProperty_Number'] = function(block) {
  var property = block.getFieldValue('PROP');
  var aprilTagDetection = Blockly.JavaScript.valueToCode(
      block, 'APRIL_TAG_DETECTION', Blockly.JavaScript.ORDER_MEMBER);
  var code = aprilTagDetection + '.' + property;
  var blockLabel = 'AprilTagDetection.' + block.getField('PROP').getText();
  return wrapJavaScriptCode(code, blockLabel);
};

Blockly.FtcJava['aprilTagDetection_getProperty_Number'] = function(block) {
  var property = block.getFieldValue('PROP');
  var aprilTagDetection = Blockly.FtcJava.valueToCode(
      block, 'APRIL_TAG_DETECTION', Blockly.FtcJava.ORDER_MEMBER);
  var code;
  switch (property) {
    case 'robotPose.position.x':
      code = aprilTagDetection + '.robotPose.getPosition().x';
      break;
    case 'robotPose.position.y':
      code = aprilTagDetection + '.robotPose.getPosition().y';
      break;
    case 'robotPose.position.z':
      code = aprilTagDetection + '.robotPose.getPosition().z';
      break;
    case 'robotPose.orientation.roll':
      code = aprilTagDetection + '.robotPose.getOrientation().getRoll()';
      break;
    case 'robotPose.orientation.pitch':
      code = aprilTagDetection + '.robotPose.getOrientation().getPitch()';
      break;
    case 'robotPose.orientation.yaw':
      code = aprilTagDetection + '.robotPose.getOrientation().getYaw()';
      break;
    default:
      code = aprilTagDetection + '.' + property;
      break;
  }
  return [code, Blockly.FtcJava.ORDER_MEMBER];
};

Blockly.Blocks['aprilTagDetection_getProperty_String'] = {
  init: function() {
    var PROPERTY_CHOICES = [
        ['metadata.name', 'metadata.name'],
    ];
    this.setOutput(true, 'String');
    this.appendDummyInput()
        .appendField(createNonEditableField('AprilTagDetection'))
        .appendField('.')
        .appendField(new Blockly.FieldDropdown(PROPERTY_CHOICES), 'PROP');
    this.appendValueInput('APRIL_TAG_DETECTION').setCheck('AprilTagDetection')
        .appendField('aprilTagDetection')
        .setAlign(Blockly.ALIGN_RIGHT);
    this.setColour(getPropertyColor);
    // Assign 'this' to a variable for use in the closures below.
    var thisBlock = this;
    var TOOLTIPS = [
        ['metadata.name', 'Returns the metadata.name field of the AprilTagDetection.'],
    ];
    this.setTooltip(function() {
      var key = thisBlock.getFieldValue('PROP');
      for (var i = 0; i < TOOLTIPS.length; i++) {
        if (TOOLTIPS[i][0] == key) {
          return TOOLTIPS[i][1];
        }
      }
      return '';
    });
  }
};

Blockly.JavaScript['aprilTagDetection_getProperty_String'] = function(block) {
  var property = block.getFieldValue('PROP');
  var aprilTagDetection = Blockly.JavaScript.valueToCode(
      block, 'APRIL_TAG_DETECTION', Blockly.JavaScript.ORDER_MEMBER);
  var code = aprilTagDetection + '.' + property;
  var blockLabel = 'AprilTagDetection.' + block.getField('PROP').getText();
  return wrapJavaScriptCode(code, blockLabel);
};

Blockly.FtcJava['aprilTagDetection_getProperty_String'] = function(block) {
  var property = block.getFieldValue('PROP');
  var aprilTagDetection = Blockly.FtcJava.valueToCode(
      block, 'APRIL_TAG_DETECTION', Blockly.FtcJava.ORDER_MEMBER);
  var code = aprilTagDetection + '.' + property;
  return [code, Blockly.FtcJava.ORDER_MEMBER];
};

Blockly.Blocks['aprilTagDetection_getProperty_IsNotNull'] = {
  init: function() {
    var PROPERTY_CHOICES = [
        ['metadata', 'metadata'],
        ['ftcPose', 'ftcPose'],
        ['rawPose', 'rawPose'],
        ['robotPose', 'robotPose'],
    ];
    this.setOutput(true, 'Boolean');
    this.appendDummyInput()
        .appendField(createNonEditableField('AprilTagDetection'))
        .appendField('.')
        .appendField(new Blockly.FieldDropdown(PROPERTY_CHOICES), 'PROP')
        .appendField('!=')
        .appendField(createNonEditableField('null'));
    this.appendValueInput('APRIL_TAG_DETECTION').setCheck('AprilTagDetection')
        .appendField('aprilTagDetection')
        .setAlign(Blockly.ALIGN_RIGHT);
    this.setColour(getPropertyColor);
    // Assign 'this' to a variable for use in the closures below.
    var thisBlock = this;
    var TOOLTIPS = [
        ['metadata', 'Returns true if the metadata field of the AprilTagDetection is not null.'],
        ['ftcPose', 'Returns true if the ftcPose field of the AprilTagDetection is not null.'],
        ['rawPose', 'Returns true if the rawPose field of the AprilTagDetection is not null.'],
        ['robotPose', 'Returns true if the robotPose field of the AprilTagDetection is not null.'],
    ];
    this.setTooltip(function() {
      var key = thisBlock.getFieldValue('PROP');
      for (var i = 0; i < TOOLTIPS.length; i++) {
        if (TOOLTIPS[i][0] == key) {
          return TOOLTIPS[i][1];
        }
      }
      return '';
    });
  }
};

Blockly.JavaScript['aprilTagDetection_getProperty_IsNotNull'] = function(block) {
  var property = block.getFieldValue('PROP');
  var aprilTagDetection = Blockly.JavaScript.valueToCode(
      block, 'APRIL_TAG_DETECTION', Blockly.JavaScript.ORDER_MEMBER);
  var code = aprilTagDetection + '.' + property + ' != null';
  var blockLabel = 'AprilTagDetection.' + block.getField('PROP').getText();
  return wrapJavaScriptCode(code, blockLabel);
};

Blockly.FtcJava['aprilTagDetection_getProperty_IsNotNull'] = function(block) {
  var property = block.getFieldValue('PROP');
  var aprilTagDetection = Blockly.FtcJava.valueToCode(
      block, 'APRIL_TAG_DETECTION', Blockly.FtcJava.ORDER_MEMBER);
  var code = aprilTagDetection + '.' + property + ' != null';
  return [code, Blockly.FtcJava.ORDER_EQUALITY];
};

Blockly.Blocks['aprilTagDetection_getProperty_AprilTagMetadata'] = {
  init: function() {
    var PROPERTY_CHOICES = [
        ['metadata', 'metadata'],
    ];
    this.setOutput(true, 'AprilTagMetadata');
    this.appendDummyInput()
        .appendField(createNonEditableField('AprilTagDetection'))
        .appendField('.')
        .appendField(new Blockly.FieldDropdown(PROPERTY_CHOICES), 'PROP');
    this.appendValueInput('APRIL_TAG_DETECTION').setCheck('AprilTagDetection')
        .appendField('aprilTagDetection')
        .setAlign(Blockly.ALIGN_RIGHT);
    this.setColour(getPropertyColor);
    // Assign 'this' to a variable for use in the closures below.
    var thisBlock = this;
    var TOOLTIPS = [
        ['metadata', 'Returns the metadata field of the AprilTagDetection.'],
    ];
    this.setTooltip(function() {
      var key = thisBlock.getFieldValue('PROP');
      for (var i = 0; i < TOOLTIPS.length; i++) {
        if (TOOLTIPS[i][0] == key) {
          return TOOLTIPS[i][1];
        }
      }
      return '';
    });
  }
};

Blockly.JavaScript['aprilTagDetection_getProperty_AprilTagMetadata'] = function(block) {
  var property = block.getFieldValue('PROP');
  var aprilTagDetection = Blockly.JavaScript.valueToCode(
      block, 'APRIL_TAG_DETECTION', Blockly.JavaScript.ORDER_MEMBER);
  var code = aprilTagDetection + '.' + property;
  var blockLabel = 'AprilTagDetection.' + block.getField('PROP').getText();
  return wrapJavaScriptCode(code, blockLabel);
};

Blockly.FtcJava['aprilTagDetection_getProperty_AprilTagMetadata'] = function(block) {
  var property = block.getFieldValue('PROP');
  var aprilTagDetection = Blockly.FtcJava.valueToCode(
      block, 'APRIL_TAG_DETECTION', Blockly.FtcJava.ORDER_MEMBER);
  var code = aprilTagDetection + '.' + property;
  return [code, Blockly.FtcJava.ORDER_MEMBER];
};

Blockly.Blocks['aprilTagDetection_getProperty_AprilTagPoseFtc'] = {
  init: function() {
    var PROPERTY_CHOICES = [
        ['ftcPose', 'ftcPose'],
    ];
    this.setOutput(true, 'AprilTagPoseFtc');
    this.appendDummyInput()
        .appendField(createNonEditableField('AprilTagDetection'))
        .appendField('.')
        .appendField(new Blockly.FieldDropdown(PROPERTY_CHOICES), 'PROP');
    this.appendValueInput('APRIL_TAG_DETECTION').setCheck('AprilTagDetection')
        .appendField('aprilTagDetection')
        .setAlign(Blockly.ALIGN_RIGHT);
    this.setColour(getPropertyColor);
    // Assign 'this' to a variable for use in the closures below.
    var thisBlock = this;
    var TOOLTIPS = [
        ['ftcPose', 'Returns the ftcPose field of the AprilTagDetection.'],
    ];
    this.setTooltip(function() {
      var key = thisBlock.getFieldValue('PROP');
      for (var i = 0; i < TOOLTIPS.length; i++) {
        if (TOOLTIPS[i][0] == key) {
          return TOOLTIPS[i][1];
        }
      }
      return '';
    });
  }
};

Blockly.JavaScript['aprilTagDetection_getProperty_AprilTagPoseFtc'] = function(block) {
  var property = block.getFieldValue('PROP');
  var aprilTagDetection = Blockly.JavaScript.valueToCode(
      block, 'APRIL_TAG_DETECTION', Blockly.JavaScript.ORDER_MEMBER);
  var o = aprilTagDetection + '.' + property;
  var code = aprilTagIdentifierForJavaScript + '.createAprilTagPoseFtc(' +
      '"GETTER", "AprilTagDetection", "' + property + '", ' +
      'JSON.stringify(' + o + '))';
  var wrappedCode = 'evalIfTruthy(' + o + ', \'' + code + '\', null)';
  return [wrappedCode, Blockly.JavaScript.ORDER_FUNCTION_CALL];
};

Blockly.FtcJava['aprilTagDetection_getProperty_AprilTagPoseFtc'] = function(block) {
  var property = block.getFieldValue('PROP');
  var aprilTagDetection = Blockly.FtcJava.valueToCode(
      block, 'APRIL_TAG_DETECTION', Blockly.FtcJava.ORDER_MEMBER);
  var code = aprilTagDetection + '.' + property;
  return [code, Blockly.FtcJava.ORDER_MEMBER];
};

Blockly.Blocks['aprilTagDetection_getProperty_AprilTagPoseRaw'] = {
  init: function() {
    var PROPERTY_CHOICES = [
        ['rawPose', 'rawPose'],
    ];
    this.setOutput(true, 'AprilTagPoseRaw');
    this.appendDummyInput()
        .appendField(createNonEditableField('AprilTagDetection'))
        .appendField('.')
        .appendField(new Blockly.FieldDropdown(PROPERTY_CHOICES), 'PROP');
    this.appendValueInput('APRIL_TAG_DETECTION').setCheck('AprilTagDetection')
        .appendField('aprilTagDetection')
        .setAlign(Blockly.ALIGN_RIGHT);
    this.setColour(getPropertyColor);
    // Assign 'this' to a variable for use in the closures below.
    var thisBlock = this;
    var TOOLTIPS = [
        ['rawPose', 'Returns the rawPose field of the AprilTagDetection.'],
    ];
    this.setTooltip(function() {
      var key = thisBlock.getFieldValue('PROP');
      for (var i = 0; i < TOOLTIPS.length; i++) {
        if (TOOLTIPS[i][0] == key) {
          return TOOLTIPS[i][1];
        }
      }
      return '';
    });
  }
};

Blockly.JavaScript['aprilTagDetection_getProperty_AprilTagPoseRaw'] = function(block) {
  var property = block.getFieldValue('PROP');
  var aprilTagDetection = Blockly.JavaScript.valueToCode(
      block, 'APRIL_TAG_DETECTION', Blockly.JavaScript.ORDER_MEMBER);
  var o = aprilTagDetection + '.' + property;
  var code = aprilTagIdentifierForJavaScript + '.createAprilTagPoseRaw(' +
      '"GETTER", "AprilTagDetection", "' + property + '", ' +
      'JSON.stringify(' + o + '))';
  var wrappedCode = 'evalIfTruthy(' + o + ', \'' + code + '\', null)';
  return [wrappedCode, Blockly.JavaScript.ORDER_FUNCTION_CALL];
};

Blockly.FtcJava['aprilTagDetection_getProperty_AprilTagPoseRaw'] = function(block) {
  var property = block.getFieldValue('PROP');
  var aprilTagDetection = Blockly.FtcJava.valueToCode(
      block, 'APRIL_TAG_DETECTION', Blockly.FtcJava.ORDER_MEMBER);
  var code = aprilTagDetection + '.' + property;
  return [code, Blockly.FtcJava.ORDER_MEMBER];
};

Blockly.Blocks['aprilTagDetection_getProperty_AprilTagPoseRobot'] = {
  init: function() {
    var PROPERTY_CHOICES = [
        ['robotPose', 'robotPose'],
    ];
    this.setOutput(true, 'AprilTagPoseRobot');
    this.appendDummyInput()
        .appendField(createNonEditableField('AprilTagDetection'))
        .appendField('.')
        .appendField(new Blockly.FieldDropdown(PROPERTY_CHOICES), 'PROP');
    this.appendValueInput('APRIL_TAG_DETECTION').setCheck('AprilTagDetection')
        .appendField('aprilTagDetection')
        .setAlign(Blockly.ALIGN_RIGHT);
    this.setColour(getPropertyColor);
    // Assign 'this' to a variable for use in the closures below.
    var thisBlock = this;
    var TOOLTIPS = [
        ['robotPose', 'Returns the robotPose field of the AprilTagDetection.'],
    ];
    this.setTooltip(function() {
      var key = thisBlock.getFieldValue('PROP');
      for (var i = 0; i < TOOLTIPS.length; i++) {
        if (TOOLTIPS[i][0] == key) {
          return TOOLTIPS[i][1];
        }
      }
      return '';
    });
  }
};

Blockly.JavaScript['aprilTagDetection_getProperty_AprilTagPoseRobot'] = function(block) {
  var property = block.getFieldValue('PROP');
  var aprilTagDetection = Blockly.JavaScript.valueToCode(
      block, 'APRIL_TAG_DETECTION', Blockly.JavaScript.ORDER_MEMBER);
  var o = aprilTagDetection + '.' + property;
  var code = aprilTagIdentifierForJavaScript + '.createAprilTagPoseRobot(' +
      '"GETTER", "AprilTagDetection", "' + property + '", ' +
      'JSON.stringify(' + o + '))';
  var wrappedCode = 'evalIfTruthy(' + o + ', \'' + code + '\', null)';
  return [wrappedCode, Blockly.JavaScript.ORDER_FUNCTION_CALL];
};

Blockly.FtcJava['aprilTagDetection_getProperty_AprilTagPoseRobot'] = function(block) {
  var property = block.getFieldValue('PROP');
  var aprilTagDetection = Blockly.FtcJava.valueToCode(
      block, 'APRIL_TAG_DETECTION', Blockly.FtcJava.ORDER_MEMBER);
  var code = aprilTagDetection + '.' + property;
  return [code, Blockly.FtcJava.ORDER_MEMBER];
};

Blockly.Blocks['aprilTagDetection_getProperty_MatrixF'] = {
  init: function() {
    var PROPERTY_CHOICES = [
        ['rawPose.R', 'rawPose.R'],
    ];
    this.setOutput(true, 'MatrixF');
    this.appendDummyInput()
        .appendField(createNonEditableField('AprilTagDetection'))
        .appendField('.')
        .appendField(new Blockly.FieldDropdown(PROPERTY_CHOICES), 'PROP');
    this.appendValueInput('APRIL_TAG_DETECTION').setCheck('AprilTagDetection')
        .appendField('aprilTagDetection')
        .setAlign(Blockly.ALIGN_RIGHT);
    this.setColour(getPropertyColor);
    // Assign 'this' to a variable for use in the closures below.
    var thisBlock = this;
    var TOOLTIPS = [
        ['rawPose.R', 'Returns the rawPose.R field of the AprilTagDetection.'],
    ];
    this.setTooltip(function() {
      var key = thisBlock.getFieldValue('PROP');
      for (var i = 0; i < TOOLTIPS.length; i++) {
        if (TOOLTIPS[i][0] == key) {
          return TOOLTIPS[i][1];
        }
      }
      return '';
    });
  }
};

Blockly.JavaScript['aprilTagDetection_getProperty_MatrixF'] = function(block) {
  var property = block.getFieldValue('PROP');
  var aprilTagDetection = Blockly.JavaScript.valueToCode(
      block, 'APRIL_TAG_DETECTION', Blockly.JavaScript.ORDER_MEMBER);
  var o = aprilTagDetection + '.' + property;
  var code = aprilTagIdentifierForJavaScript + '.createMatrixF(' +
      '"GETTER", "AprilTagDetection", "' + property + '", ' +
      'JSON.stringify(' + o + '))';
  var wrappedCode = 'evalIfTruthy(' + o + ', \'' + code + '\', null)';
  return [wrappedCode, Blockly.JavaScript.ORDER_FUNCTION_CALL];
};

Blockly.FtcJava['aprilTagDetection_getProperty_MatrixF'] = function(block) {
  var property = block.getFieldValue('PROP');
  var aprilTagDetection = Blockly.FtcJava.valueToCode(
      block, 'APRIL_TAG_DETECTION', Blockly.FtcJava.ORDER_MEMBER);
  var code = aprilTagDetection + '.' + property;
  return [code, Blockly.FtcJava.ORDER_MEMBER];
};

