//
//  LoginViewController.h
//  hull-scout
//
//  Created by Zaki Shaheen on 2/21/15.
//  Copyright (c) 2015 Zaki Shaheen. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface LoginViewController : UIViewController
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *loginButton;
- (IBAction)loginFacebook:(id)sender;

@end
