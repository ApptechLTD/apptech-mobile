//
//  UserData.h
//  MSLApp-Native-IOS
//
//  Created by Chen Pengfei on 2/03/13.
//
//
#import <Foundation/Foundation.h>
#import <Cordova/CDVPlugin.h>

@interface UserSetting : CDVPlugin
{
    NSMutableDictionary* _userSettings;
}

- (void)save:(CDVInvokedUrlCommand*)command;
- (void)read:(CDVInvokedUrlCommand*)command;

@end
