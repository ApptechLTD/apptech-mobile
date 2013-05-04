//
//  HandleDocument.m
//  MSLApp-Native-IOS
//
//  Created by Júlio Adrian Miño Van Helden on 27/02/13.
//
//

#import "HandleDocument.h"
#import "DocumentViewController.h"
#import "PW_SBJsonWriter.h"

@interface HandleDocument () {
    
    DocumentViewController* _handleDocumentViewController;
    NSString * _callbackId;
}

@end

@implementation HandleDocument

- (void)showDocument:(NSMutableArray *)arguments withDict:(NSMutableDictionary*)options {
    
    NSLog(@" %@ " , options);
    
    _callbackId = [arguments pop];
    
    _handleDocumentViewController =
    [[DocumentViewController alloc] initWithNibName:@"DocumentViewController" bundle:[NSBundle mainBundle]];
    
    [self.viewController presentModalViewController:_handleDocumentViewController animated:YES];
    
    _handleDocumentViewController.webView.scalesPageToFit=YES;
    
    [_handleDocumentViewController.webView loadRequest:
                                           [NSURLRequest requestWithURL:[NSURL URLWithString:options[@"url"] ]]];
    
    _handleDocumentViewController.webView.delegate = self;
    
    // Back button define
    UIBarButtonItem* item = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemDone target:self action:@selector(onBackButton)];
    
    UINavigationItem* navItem = [[UINavigationItem alloc] initWithTitle:@"Mail"];
    [_handleDocumentViewController.navigationBar pushNavigationItem:navItem animated:NO];
    navItem.leftBarButtonItem = item;
    
    //CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:nil];
    //[self writeJavascript:[pluginResult toSuccessCallbackString:_callbackId]];
}

-(void) onBackButton
{
   [_handleDocumentViewController dismissModalViewControllerAnimated:YES]; 
}

- (void)webViewDidFinishLoad:(UIWebView *)webView
{
    //[self performSelectorOnMainThread:@selector(callbackSuccess) withObject:self waitUntilDone:NO];

}

-(void) callbackSuccess
{
    //CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:nil];
    //[self writeJavascript:[pluginResult toSuccessCallbackString:_callbackId]];
}

- (void)webView:(UIWebView *)webView didFailLoadWithError:(NSError *)error
{
    //CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:nil];
    //[self writeJavascript:[pluginResult toErrorCallbackString:_callbackId]];
}

@end
