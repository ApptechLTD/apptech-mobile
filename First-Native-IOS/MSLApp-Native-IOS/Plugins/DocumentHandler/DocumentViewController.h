//
//  DocumentViewController.h
//  MSLApp-Native-IOS
//
//  Created by Júlio Adrian Miño Van Helden on 28/02/13.
//
//

#import <UIKit/UIKit.h>

@interface DocumentViewController : UIViewController
{
    UIWebView* webView;
}

@property (nonatomic, strong) IBOutlet UIWebView* webView;
@property (nonatomic, strong) IBOutlet UINavigationBar* navigationBar;

@end
