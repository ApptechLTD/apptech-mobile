//
//  HandleDocument.h
//  MSLApp-Native-IOS
//
//  Created by Júlio Adrian Miño Van Helden on 27/02/13.
//
//

#import <Foundation/Foundation.h>
#import <Cordova/CDVPlugin.h>

@interface HandleDocument : CDVPlugin <UIWebViewDelegate>

- (void)showDocument:(NSMutableArray *)arguments withDict:(NSMutableDictionary*)options;

@end
