//
//  HomeProfileViewController.m
//  hull-scout
//
//  Created by Zaki Shaheen on 2/21/15.
//  Copyright (c) 2015 Zaki Shaheen. All rights reserved.
//

#import "HomeProfileViewController.h"
#import <FacebookSDK/FacebookSDK.h>
#import <ParseFacebookUtils/PFFacebookUtils.h>
#import "AsyncImageView.h"
#import <Parse/Parse.h>
#import "MissionCellTableViewCell.h"

@interface HomeProfileViewController ()
@property (weak, nonatomic) IBOutlet UILabel *usernameWelcomeField;
@property (weak, nonatomic) IBOutlet UILabel *createdMissions;
@property (weak, nonatomic) IBOutlet UILabel *collabMissions;
@property (weak, nonatomic) IBOutlet AsyncImageView *profileImageView;
@property (strong, nonatomic) NSMutableArray *mymissions;

@end

@implementation HomeProfileViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    PFUser *currentUser  = [PFUser currentUser];
    
    self.usernameWelcomeField.text = [NSString stringWithFormat:@"Welcome %@!", currentUser[@"name"]];
    self.createdMissions.text = @"5";
    self.collabMissions.text = @"9";
    

    
    [FBRequestConnection startForMeWithCompletionHandler:^(FBRequestConnection *connection, id result, NSError *error) {

        
        NSString *userImageURL = [NSString stringWithFormat:@"https://graph.facebook.com/%@/picture?type=large", [result objectForKey:@"id"] ];
        
        self.profileImageView.layer.cornerRadius = self.profileImageView.frame.size.width / 2;
        self.profileImageView.clipsToBounds = YES;
        
        self.profileImageView.imageURL  = [NSURL URLWithString:userImageURL];
        
        
        ;
    }];
    
    
    self.title = @"Profile";
    
    UIBarButtonItem *addItem = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemAdd
                                                                             target:self action:@selector(addMission:)];

    [self.navigationItem setHidesBackButton:YES];
    
    [self.navigationItem setRightBarButtonItem:addItem];
    
    [self getMissions];
    

}

- (void) getMissions{
    PFQuery *query = [[PFQuery alloc] initWithClassName:@"Mission"];
    [query whereKey:@"user" equalTo:[PFUser currentUser]];
    
    [query findObjectsInBackgroundWithBlock:^(NSArray *objects, NSError *error) {
        self.mymissions = [objects copy];
        [self.missionsTable reloadData];
    }];
    
    
}

-(void)addMission:(id) sender{
    [self performSegueWithIdentifier:@"addMission" sender:self];
}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return [self.mymissions count];
}


- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 150;
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    MissionCellTableViewCell * cell = [tableView dequeueReusableCellWithIdentifier:@"missionCard"];
    
    if (!cell) {
        
        cell = (MissionCellTableViewCell*)[[[NSBundle mainBundle] loadNibNamed:@"MissionCell" owner:nil options:nil] objectAtIndex:0];
        
        
        
    }
    
    PFObject *o  = [self.mymissions objectAtIndex:indexPath.row];
    
    cell.titleLabel.text = o[@"title"];
    cell.categoryLabel.text = o[@"category"];
    cell.RSVPLabel.text = [NSString stringWithFormat:@"%2d",rand() % 25];
    
    
    NSArray *locations = @[@"Commercial Centre,Satellite Town, Islamabad",
                           @"House No.6A,St.54/A,F-7/4",
                           @"# 19,ST # 28,F-6/1", @"H.No.13,Kaghan, Road,Near Church,F-8/3",
                           @"House #13,Street #31,F-7/1",
                           @"House No.23,Street 25,F-8/2"];
    
    int locIndex = rand() % 5;
    cell.addressLabel.text = [locations objectAtIndex:locIndex];
    
    return cell;
}

- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section{
    return @"My missions";
}
/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
