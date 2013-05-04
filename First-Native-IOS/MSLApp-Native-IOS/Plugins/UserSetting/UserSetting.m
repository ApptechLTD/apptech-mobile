//
//  UserData.m
//  MSLApp-Native-IOS
//
//  Created by Chen Pengfei on 2/03/13.
//
//

#import "UserSetting.h"

#define UserSettingFileName @"usersetting.plist"
#define Key_UserSetting_ServerUrl @"serverUrl"
#define DefaultUrl @"http://172.20.10.7:8080"
@implementation UserSetting

- (void)save:(CDVInvokedUrlCommand*)command
{
    
}

// Read the user setting dictionary
- (void)read:(CDVInvokedUrlCommand*)command
{
    NSDictionary* readSetting = [self userSettings];
    
    // Call the read data to js
    CDVPluginResult* pluginResult = nil;
    if (readSetting != nil)
    {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:readSetting];
       
    }
    else
    {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"User setting error!"];
    }
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

// Get the user setting data, if data not exist, create a default one
-(NSDictionary*) userSettings
{
    // Now the setting was moved to the gwt project
    _userSettings = [NSDictionary dictionaryWithObjectsAndKeys:@"", Key_UserSetting_ServerUrl, nil];
    
//    // Set the application defaults
//    NSUserDefaults *defaults = [NSUserDefaults standardUserDefaults];
//    
//    //verify if the app URL is not specified
//    NSString *serverurl = [defaults stringForKey:@"url_preference"];
//    
//    if(! serverurl || serverurl.length == 0){
//        
//        //set the default App URL for the first app install/setup
//        serverurl = DefaultUrl;
//        [defaults setObject:DefaultUrl forKey:@"url_preference"];
//        [defaults synchronize];
//    }
//
//    _userSettings = [NSDictionary dictionaryWithObjectsAndKeys:serverurl, Key_UserSetting_ServerUrl, nil];
    
    return _userSettings;
}

@end
