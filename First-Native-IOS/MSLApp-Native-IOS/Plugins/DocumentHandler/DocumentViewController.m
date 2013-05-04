//
//  DocumentViewController.m
//  MSLApp-Native-IOS
//
//  Created by Júlio Adrian Miño Van Helden on 28/02/13.
//
//

#import "DocumentViewController.h"

@interface DocumentViewController ()
{
    NSURL* _url;
}


@end



@implementation DocumentViewController

@synthesize webView;

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    
   // [self.webView loadRequest:[NSURLRequest requestWithURL:_url]];
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


- (id) initWithURL:(NSString *)url {
	self = [super init];
    
    _url = [NSURL URLWithString:url];
    
    return  self;
}
@end
