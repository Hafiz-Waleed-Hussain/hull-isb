//
//  MissionCellTableViewCell.h
//  hull-scout
//
//  Created by Zaki Shaheen on 2/22/15.
//  Copyright (c) 2015 Zaki Shaheen. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface MissionCellTableViewCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UILabel *categoryLabel;
@property (weak, nonatomic) IBOutlet UILabel *titleLabel;
@property (weak, nonatomic) IBOutlet UILabel *addressLabel;
@property (weak, nonatomic) IBOutlet UILabel *timeLabel;
@property (weak, nonatomic) IBOutlet UILabel *userLabel;
@property (weak, nonatomic) IBOutlet UILabel *RSVPLabel;

@end
