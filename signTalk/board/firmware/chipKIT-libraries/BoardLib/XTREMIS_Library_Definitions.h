//
//  Definitions_32.h
//
//
//  Created by Conor Russomanno, Luke Travis, and Joel Murphy. Summer 2013.
//	Modified by Joel Murphy, Summer 2014
//	Joel Added Daisy Functionality Fall 2014
//

#ifndef _____XTREMIS_Library_Definitions_h
#define _____XTREMIS_Library_Definitions_h

// The default baud rate
#define XTREMIS_BAUD_RATE 115200
#define HIGH_OUTPUT_BAUD_RATE 500000
// File transmissions
#define XTREMIS_BOP 'A' // Begining of stream packet
#define XTREMIS_EOP_STND_ACCEL          0xC0 // End of standard stream packet
#define XTREMIS_EOP_STND_RAW_AUX        0xC1 // End of stream packet with raw packet
#define XTREMIS_EOP_USER_DEFINED        0xC2 // End of stream packet, user defined
#define XTREMIS_EOP_ACCEL_TIME_SET      0xC3 // End of time sync up with accel stream packet
#define XTREMIS_EOP_ACCEL_TIME_SYNCED   0xC4 // End of time synced stream packet
#define XTREMIS_EOP_RAW_AUX_TIME_SET    0xC5 // End of time sync up stream packet
#define XTREMIS_EOP_RAW_AUX_TIME_SYNCED 0xC6 // End of time synced stream packet

//PIN CONNECTIONS
#define ADS_DRDY 	    9   // ADS data ready pin
#define ADS_RST	        4   // ADS reset pin
#define BOARD_ADS	    8   // ADS chip select
#define DAISY_ADS	    3   // ADS Daisy chip select
#define BOTH_ADS        5	// Slave Select Both ADS chips
#define SD_SS	        2  	// SD card chip select
#define LSM6DS0_SS	    1   // LSM6DS0 chip select
#define LSM6DS0_DRDY	    0	// LSM6DS0 data ready pin
#define XTREMIS_PIN_LED 11
#define XTREMIS_PIN_PGC 12


//ADS1299 SPI Command Definition Byte Assignments
#define _WAKEUP 0x02 // Wake-up from standby mode
#define _STANDBY 0x04 // Enter Standby mode
#define _RESET 0x06 // Reset the device registers to default
#define _START 0x08 // Start and restart (synchronize) conversions
#define _STOP 0x0A // Stop conversion
#define _RDATAC 0x10 // Enable Read Data Continuous mode (default mode at power-up)
#define _SDATAC 0x11 // Stop Read Data Continuous mode
#define _RDATA 0x12 // Read data by command supports multiple read back

//ASD1299 Register Addresses
#define ADS_ID	0x3E	// product ID for ADS1299
#define ID_REG  0x00	// this register contains ADS_ID
#define CONFIG1 0x01
#define CONFIG2 0x02
#define CONFIG3 0x03
#define LOFF 0x04
#define CH1SET 0x05
#define CH2SET 0x06
#define CH3SET 0x07
#define CH4SET 0x08
#define CH5SET 0x09
#define CH6SET 0x0A
#define CH7SET 0x0B
#define CH8SET 0x0C
#define BIAS_SENSP 0x0D
#define BIAS_SENSN 0x0E
#define LOFF_SENSP 0x0F
#define LOFF_SENSN 0x10
#define LOFF_FLIP 0x11
#define LOFF_STATP 0x12
#define LOFF_STATN 0x13
#define GPIO 0x14
#define MISC1 0x15
#define MISC2 0x16
#define CONFIG4 0x17

#define OUTPUT_NOTHING (0)  // quiet
#define OUTPUT_8_CHAN (1)   // not using Daisy module
#define OUTPUT_16_CHAN (2)  // using Daisy module
#define ON_BOARD	(8)			  // slave address for on board ADS
#define ON_DAISY	(3)				// slave address for daisy ADS
// CHANNEL SETTINGS
#define POWER_DOWN      (0)
#define GAIN_SET        (1)
#define INPUT_TYPE_SET  (2)
#define BIAS_SET        (3)
#define SRB2_SET        (4)
#define SRB1_SET        (5)
#define YES      	(0x01)
#define NO      	(0x00)

//gainCode choices
#define ADS_GAIN01 (0b00000000)	// 0x00
#define ADS_GAIN02 (0b00010000)	// 0x10
#define ADS_GAIN04 (0b00100000)	// 0x20
#define ADS_GAIN06 (0b00110000)	// 0x30
#define ADS_GAIN08 (0b01000000)	// 0x40
#define ADS_GAIN12 (0b01010000)	// 0x50
#define ADS_GAIN24 (0b01100000)	// 0x60

//inputType choices
#define ADSINPUT_NORMAL     (0b00000000)
#define ADSINPUT_SHORTED    (0b00000001)
#define ADSINPUT_BIAS_MEAS  (0b00000010)
#define ADSINPUT_MVDD       (0b00000011)
#define ADSINPUT_TEMP       (0b00000100)
#define ADSINPUT_TESTSIG    (0b00000101)
#define ADSINPUT_BIAS_DRP   (0b00000110)
#define ADSINPUT_BIAL_DRN   (0b00000111)

//test signal choices...ADS1299 datasheet page 41
#define ADSTESTSIG_AMP_1X (0b00000000)
#define ADSTESTSIG_AMP_2X (0b00000100)
#define ADSTESTSIG_PULSE_SLOW (0b00000000)
#define ADSTESTSIG_PULSE_FAST (0b00000001)
#define ADSTESTSIG_DCSIG (0b00000011)
#define ADSTESTSIG_NOCHANGE (0b11111111)

//Lead-off signal choices
#define LOFF_MAG_6NA        (0b00000000)
#define LOFF_MAG_24NA       (0b00000100)
#define LOFF_MAG_6UA        (0b00001000)
#define LOFF_MAG_24UA       (0b00001100)
#define LOFF_FREQ_DC        (0b00000000)
#define LOFF_FREQ_7p8HZ     (0b00000001)
#define LOFF_FREQ_31p2HZ    (0b00000010)
#define LOFF_FREQ_FS_4      (0b00000011)
#define PCHAN (0)
#define NCHAN (1)
#define OFF (0)
#define ON (1)

// Sample rates
#define NO_CLK_250SPS 0x96
#define NO_CLK_500SPS 0x95
#define NO_CLK_1KSPS  0x94
#define NO_CLK_2KSPS  0x93
#define NO_CLK_4KSPS  0x92
#define NO_CLK_8KSPS  0x91
#define NO_CLK_16KSPS 0x90

// used for channel settings
#define ACTIVATE_SHORTED (2)
#define ACTIVATE (1)
#define DEACTIVATE (0)

#define PCKT_START 0xA0	// prefix for data packet error checking
#define PCKT_END 0xC0	// postfix for data packet error checking

// daisy module
#define CLK_EN	5

//LSM6DS0 (Accel + Gyro)
#define READ_REG		0x80
#define READ_MULTI		0x40
#define ACT_THS          0x04    // Activity Threshold Register
#define ACT_DUR          0x05    // Activity Duration Register
#define INT_GEN_CFG_XL   0x06    // Linear acceleration sensor interrupt generator configuration register. 
#define INT_GEN_THS_X_XL 0x07    // Linear acceleration sensor interrupt threshold register -- X axis.
#define INT_GEN_THS_Y_XL 0x08    // Linear acceleration sensor interrupt threshold register -- Y axis.
#define INT_GEN_THS_Z_XL 0x09    // Linear acceleration sensor interrupt threshold register -- Z axis.
#define INT_GEN_DUR_XL   0x0A    // Linear acceleration sensor interrupt duration register.
#define REFERENCE_G      0x0B    // Angular rate sensor reference value register for digital high-pass filter (r/w)
#define INT_CTRL         0x0C    // INT pin control register.
#define WHO_AM_I		 0x0F	 // DEVICE ID 0x33
#define CTRL_REG1_G      0x10    // Angular rate sensor control register 1.
#define CTRL_REG2_G      0x11    // Angular rate sensor control register 2.
#define CTRL_REG3_G      0x12    // Angular rate sensor control register 3.
#define ORIENT_CFG_G     0x13    // Angular rate sensor sign and orientation register.
#define INT_GEN_SRC_G    0x14    // Angular rate sensor interrupt source register. 
#define OUT_TEMP_L       0x15    // Temperature data output register. L and H registers together express a 16-bit word in two?s complement right-justified.
#define OUT_TEMP_H       0x16
#define STATUS_REG       0x17    // Status register.
#define OUT_X_G          0x18    // Angular rate sensor pitch axis (X) angular rate output register. The value is expressed as a 16-bit word in two?s complement. (18-19))
#define OUT_Y_G          0x1A    // Angular rate sensor roll axis (Y) angular rate output register. The value is expressed as a 16-bit word in two?s complement. (A-B)
#define OUT_Z_G          0x1C    // Angular rate sensor yaw axis (Z) angular rate output register. The value is expressed as a 16-bit word in two?s complement. (C-D))
#define CTRL_REG4        0x1E    // Control register 
#define CTRL_REG5_XL     0x1F    // Linear acceleration sensor Control Register 5. 
#define CTRL_REG6_XL     0x20    // Linear acceleration sensor Control Register 6.
#define CTRL_REG7_XL     0x21    // Linear acceleration sensor Control Register 7. 
#define CTRL_REG8        0x22    // Control Register 8
#define CTRL_REG9        0x23    // Control Register 9
#define CTRL_REG10       0x24    // Control Register 10
#define INT_GEN_SRC_XL   0x26    // Linear acceleration sensor interrupt source register. 
#define STATUS_REG_ACC   0x27    // Status Register for accel
#define OUT_X_XL         0x28    // Linear acceleration sensor X-axis output register. The value is expressed as a 16-bit word in two?s complement. (28-29)
#define OUT_Y_XL         0x2A    // Linear acceleration sensor Y-axis output register. The value is expressed as a 16-bit word in two?s complement. (2A-2B)
#define OUT_Z_XL         0x2C    // Linear acceleration sensor Z-axis output register. The value is expressed as a 16-bit word in two?s complement. (2C-2D)
#define FIFO_REG         0x2E    // FIFO control register
#define FIFO_SRC         0x2F    // FIFO status control register
#define INT_GEN_CFG_G    0x30    // Angular rate sensor interrupt generator configuration register. 
#define INT_GEN_THS_X_G  0x31    // Angular rate sensor interrupt generator threshold registers. The value is expressed as a 15-bit word in two?s complement. (31-32)
#define INT_GEN_THS_Y_G  0x33    // Angular rate sensor interrupt generator threshold registers. The value is expressed as a 15-bit word in two?s complement. (33-34)
#define INT_GEN_THS_Z_G  0x35    // Angular rate sensor interrupt generator threshold registers. The value is expressed as a 15-bit word in two?s complement. (35-36)
#define INT_GEN_DUR_G    0x36    // Angular rate sensor interrupt generator duration register.


/** LSM6DS0_XG Gyroscope?s Z-axis output enable selection CTRL_REG4
 * 
 */
#define LSM6DS0_G_ZEN_DISABLE                          0x00 /*!< Gyroscope?s Z-axis output enable: disable */
#define LSM6DS0_G_ZEN_ENABLE                           0x20 /*!< Gyroscope?s Z-axis output enable: enable */

#define LSM6DS0_G_ZEN_MASK                             0x20


/** LSM6DS0_XG Gyroscope?s Y-axis output enable selection CTRL_REG4
 * 
 */
#define LSM6DS0_G_YEN_DISABLE                          0x00 /*!< Gyroscope?s Y-axis output enable: disable */
#define LSM6DS0_G_YEN_ENABLE                           0x10 /*!< Gyroscope?s Y-axis output enable: enable */

#define LSM6DS0_G_YEN_MASK                             0x10

/**  LSM6DS0_XG Gyroscope?s X-axis output enable selection CTRL_REG4
 * 
 */
#define LSM6DS0_G_XEN_DISABLE                          0x00 /*!< Gyroscope?s X-axis output enable: disable */
#define LSM6DS0_G_XEN_ENABLE                           0x08 /*!< Gyroscope?s X-axis output enable: enable */

#define LSM6DS0_G_XEN_MASK                             0x08


// Gyroscope data rates
#define LSM6DS0_G_ODR_PD                                0x00 // Output Data Rate: Power-down
#define LSM6DS0_G_ODR_14_9HZ                            0x20 // Output Data Rate: 14.9 Hz, cutoff 5Hz
#define LSM6DS0_G_ODR_59_5HZ                            0x40 // Output Data Rate: 59.5 Hz, cutoff 19Hz
#define LSM6DS0_G_ODR_119HZ                             0x60 // Output Data Rate: 119 Hz, cutoff 38Hz
#define LSM6DS0_G_ODR_238HZ                             0x80 // Output Data Rate: 238 Hz, cutoff 76Hz
#define LSM6DS0_G_ODR_476HZ                             0xA0 // Output Data Rate: 476 Hz, cutoff 100Hz
#define LSM6DS0_G_ODR_952HZ                             0xC0 // Output Data Rate: 952 Hz, cutoff 100Hz

#define LSM6DS0_G_ODR_MASK                              0xE0

// Gyroscope Full scale selection CTRL_REG1_G
#define LSM6DS0_G_FS_245                               0x00 // Full scale: 245 dps
#define LSM6DS0_G_FS_500                               0x08 // Full scale: 500 dps 
#define LSM6DS0_G_FS_2000                              0x18 // Full scale: 2000 dps 

#define LSM6DS0_G_FS_MASK                              0x18


// Accelerometer output data rate selection
#define LSM6DS0_XL_ODR_PD                               0x09 /*!< Output Data Rate: Power-down*/
#define LSM6DS0_XL_ODR_10HZ                             0x20 /*!< Output Data Rate: 10 Hz*/
#define LSM6DS0_XL_ODR_50HZ                             0x40 /*!< Output Data Rate: 50 Hz */
#define LSM6DS0_XL_ODR_119HZ                            0x60 /*!< Output Data Rate: 119 Hz */
#define LSM6DS0_XL_ODR_238HZ                            0x80 /*!< Output Data Rate: 238 Hz */
#define LSM6DS0_XL_ODR_476HZ                            0xA0 /*!< Output Data Rate: 476 Hz */
#define LSM6DS0_XL_ODR_952HZ                            0xC0 /*!< Output Data Rate: 952 Hz */

#define LSM6DS0_XL_ODR_MASK                             0xE0
/**  LSM6DS0_XG Accelerometer Full scale selection CTRL_REG6_XL
 * 
 */
#define LSM6DS0_XL_FS_2G                                0x00 /*!< Full scale: +- 2g */
#define LSM6DS0_XL_FS_4G                                0x10 /*!< Full scale: +- 4g */
#define LSM6DS0_XL_FS_8G                                0x18 /*!< Full scale: +- 8g */

#define LSM6DS0_XL_FS_MASK                              0x18

/** LSM6DS0_XG Accelerometer?s Z-axis output enable selection CTRL_REG5_XL
 * 
 */
#define LSM6DS0_XL_ZEN_DISABLE                          ((uint8_t)0x00) /*!< Accelerometer?s Z-axis output enable: disable */
#define LSM6DS0_XL_ZEN_ENABLE                           ((uint8_t)0x20) /*!< Accelerometer?s Z-axis output enable: enable */

#define LSM6DS0_XL_ZEN_MASK                             ((uint8_t)0x20)


/** LSM6DS0_XG Accelerometer?s Y-axis output enable selection CTRL_REG5_XL
 * 
 */
#define LSM6DS0_XL_YEN_DISABLE                          ((uint8_t)0x00) /*!< Accelerometer?s Y-axis output enable: disable */
#define LSM6DS0_XL_YEN_ENABLE                           ((uint8_t)0x10) /*!< Accelerometer?s Y-axis output enable: enable */

#define LSM6DS0_XL_YEN_MASK                             ((uint8_t)0x10)


/** LSM6DS0_XG Accelerometer?s X-axis output enable selection CTRL_REG5_XL
 * 
 */
#define LSM6DS0_XL_XEN_DISABLE                          ((uint8_t)0x00) /*!< Accelerometer?s X-axis output enable: disable */
#define LSM6DS0_XL_XEN_ENABLE                           ((uint8_t)0x08) /*!< Accelerometer?s X-axis output enable: enable */

#define LSM6DS0_XL_XEN_MASK                             ((uint8_t)0x08)


// OLD ACCEL
/*
#define READ_REG		0x80
#define READ_MULTI		0x40

#define STATUS_REG_AUX	0x07	// axis over-run and data available flags (see 0x27)
#define OUT_ADC1_L		0x08	//
#define OUT_ADC1_H		0x09	//
#define OUT_ADC2_L		0x0A	//	ADC input values (check DS)
#define OUT_ADC2_H		0x0B	//
#define OUT_ADC3_L		0x0C	//
#define OUT_ADC3_H		0x0D	//
#define INT_COUNTER_REG	0x0E	// ??
#define WHO_AM_I        0x0F
#define TMP_CFG_REG		0x1F	// ADC enable (0x80) Temperature sensor enable (0x40)
#define CTRL_REG1		0x20	// Data Rate Power Mode X enable Y enable Z enable (on >0x10)
#define CTRL_REG2		0x21	// High Pass Filter Stuph
#define CTRL_REG3		0x22	// INT1 select register
#define CTRL_REG4		0x23	// Block update timing endian G-force resolution self test SPI pins
#define CTRL_REG5		0x24	// reboot FIFO enable latch 4D detection
#define CTRL_REG6		0x25	// ??
#define REFERENCE		0x26	// interrupt reference
#define STATUS_REG2		0x27	// axis overrun and availale flags (see 0x07)
#define OUT_X_L			0x28	//
#define OUT_X_H			0x29	//
#define OUT_Y_L			0x2A	//	tripple axis values (see 0x0A)
#define OUT_Y_H			0x2B	//
#define OUT_Z_L			0x2C	//
#define OUT_Z_H			0x2D	//
#define FIFO_CTRL_REG	0x2E	// FIFO mode trigger output pin select (?)
#define FIFO_SRC_REG	0x2F	// ??
#define INT1_CFG		0x30	// 6 degree control register
#define INT1_SOURCE		0x31	// axis threshold interrupt control
#define INT1_THS		0x32	// INT1 threshold
#define INT1_DURATION	0x33	// INT1 duration
 */
#define CLICK_CFG		0x38	// click on axis
#define CLICK_SRC		0x39	// other click
#define CLICK_THS		0x3A	// more click
 
#define TIME_LIMIT		0x3B	// click related
#define TIME_LATENCY	0x3C	// and so on
#define TIME_WINDOW		0x3D	// contined click
/*
#define SCALE_2G		0x00  //(b00000000)	// +/- 2G sensitivity
#define SCALE_4G		0x10  //(b00010000)	// +/- 4G sensitivity
#define SCALE_8G		0x20  //(b00100000)	// +/- 8G sensitivity
#define SCALE_16G		0x30  //(b00110000)	// +/- 16G sensitivity
#define RATE_1HZ		0x10  //(b00010000)	// 1Hz sample rate in normal or low-power mode
#define RATE_10HZ		0x20  //(b00100000)	// 10Hz sample rate in normal or low-power mode
#define RATE_25HZ		0x30  //(b00110000)	// 25Hz sample rate in normal or low-power mode
#define RATE_50HZ		0x40  //(b01000000)	// 50Hz sample rate in normal or low-power mode
#define RATE_100HZ		0x50  //(b01010000)	// 100Hz sample rate in normal or low-power mode
#define RATE_200HZ		0x60  //(b01100000)	// 200Hz sample rate in normal or low-power mode
#define RATE_400HZ		0x70  //(b01110000)	// 400Hz sample rate in normal or low-power mode
#define RATE_1600HZ_LP	0x80  //(b10000000)	// 1600Hz sample rate in low-power mode
#define RATE_1250HZ_N	0x90  //(b10010000)	// 1250Hz sample rate in normal mode
#define RATE_5000HZ_LP	0x90  //(b10010000)	// 5000Hz sample rate in low-power mode
 */
#define ACCEL_AXIS_X    0x07 // x axis
#define ACCEL_AXIS_Y    0x08 // y axis
#define ACCEL_AXIS_Z    0x09 // z axis

// XTREMIS_COMMANDS
/** Turning channels off */
#define XTREMIS_CHANNEL_OFF_1 '1'
#define XTREMIS_CHANNEL_OFF_2 '2'
#define XTREMIS_CHANNEL_OFF_3 '3'
#define XTREMIS_CHANNEL_OFF_4 '4'
#define XTREMIS_CHANNEL_OFF_5 '5'
#define XTREMIS_CHANNEL_OFF_6 '6'
#define XTREMIS_CHANNEL_OFF_7 '7'
#define XTREMIS_CHANNEL_OFF_8 '8'
#define XTREMIS_CHANNEL_OFF_9 'q'
#define XTREMIS_CHANNEL_OFF_10 'w'
#define XTREMIS_CHANNEL_OFF_11 'e'
#define XTREMIS_CHANNEL_OFF_12 'r'
#define XTREMIS_CHANNEL_OFF_13 't'
#define XTREMIS_CHANNEL_OFF_14 'y'
#define XTREMIS_CHANNEL_OFF_15 'u'
#define XTREMIS_CHANNEL_OFF_16 'i'

/** Turn channels on */
#define XTREMIS_CHANNEL_ON_1 '!'
#define XTREMIS_CHANNEL_ON_2 '@'
#define XTREMIS_CHANNEL_ON_3 '#'
#define XTREMIS_CHANNEL_ON_4 '$'
#define XTREMIS_CHANNEL_ON_5 '%'
#define XTREMIS_CHANNEL_ON_6 '^'
#define XTREMIS_CHANNEL_ON_7 '&'
#define XTREMIS_CHANNEL_ON_8 '*'
#define XTREMIS_CHANNEL_ON_9 'Q'
#define XTREMIS_CHANNEL_ON_10 'W'
#define XTREMIS_CHANNEL_ON_11 'E'
#define XTREMIS_CHANNEL_ON_12 'R'
#define XTREMIS_CHANNEL_ON_13 'T'
#define XTREMIS_CHANNEL_ON_14 'Y'
#define XTREMIS_CHANNEL_ON_15 'U'
#define XTREMIS_CHANNEL_ON_16 'I'

/** Test Signal Control Commands
 * 1x - Voltage will be 1 * (VREFP - VREFN) / 2.4 mV
 * 2x - Voltage will be 2 * (VREFP - VREFN) / 2.4 mV
 */
#define XTREMIS_TEST_SIGNAL_CONNECT_TO_DC            'p'
#define XTREMIS_TEST_SIGNAL_CONNECT_TO_GROUND        '0'
#define XTREMIS_TEST_SIGNAL_CONNECT_TO_PULSE_1X_FAST '='
#define XTREMIS_TEST_SIGNAL_CONNECT_TO_PULSE_1X_SLOW '-'
#define XTREMIS_TEST_SIGNAL_CONNECT_TO_PULSE_2X_FAST ']'
#define XTREMIS_TEST_SIGNAL_CONNECT_TO_PULSE_2X_SLOW '['

/** Channel Setting Commands */
#define XTREMIS_CHANNEL_CMD_ADC_Normal      '0'
#define XTREMIS_CHANNEL_CMD_ADC_Shorted     '1'
#define XTREMIS_CHANNEL_CMD_ADC_BiasDRP     '6'
#define XTREMIS_CHANNEL_CMD_ADC_BiasDRN     '7'
#define XTREMIS_CHANNEL_CMD_ADC_BiasMethod  '2'
#define XTREMIS_CHANNEL_CMD_ADC_MVDD        '3'
#define XTREMIS_CHANNEL_CMD_ADC_Temp        '4'
#define XTREMIS_CHANNEL_CMD_ADC_TestSig     '5'
#define XTREMIS_CHANNEL_CMD_BIAS_INCLUDE    '1'
#define XTREMIS_CHANNEL_CMD_BIAS_REMOVE     '0'
#define XTREMIS_CHANNEL_CMD_CHANNEL_1       '1'
#define XTREMIS_CHANNEL_CMD_CHANNEL_2       '2'
#define XTREMIS_CHANNEL_CMD_CHANNEL_3       '3'
#define XTREMIS_CHANNEL_CMD_CHANNEL_4       '4'
#define XTREMIS_CHANNEL_CMD_CHANNEL_5       '5'
#define XTREMIS_CHANNEL_CMD_CHANNEL_6       '6'
#define XTREMIS_CHANNEL_CMD_CHANNEL_7       '7'
#define XTREMIS_CHANNEL_CMD_CHANNEL_8       '8'
#define XTREMIS_CHANNEL_CMD_CHANNEL_9       'Q'
#define XTREMIS_CHANNEL_CMD_CHANNEL_10      'W'
#define XTREMIS_CHANNEL_CMD_CHANNEL_11      'E'
#define XTREMIS_CHANNEL_CMD_CHANNEL_12      'R'
#define XTREMIS_CHANNEL_CMD_CHANNEL_13      'T'
#define XTREMIS_CHANNEL_CMD_CHANNEL_14      'Y'
#define XTREMIS_CHANNEL_CMD_CHANNEL_15      'U'
#define XTREMIS_CHANNEL_CMD_CHANNEL_16      'I'
#define XTREMIS_CHANNEL_CMD_GAIN_1          '0'
#define XTREMIS_CHANNEL_CMD_GAIN_2          '1'
#define XTREMIS_CHANNEL_CMD_GAIN_4          '2'
#define XTREMIS_CHANNEL_CMD_GAIN_6          '3'
#define XTREMIS_CHANNEL_CMD_GAIN_8          '4'
#define XTREMIS_CHANNEL_CMD_GAIN_12         '5'
#define XTREMIS_CHANNEL_CMD_GAIN_24         '6'
#define XTREMIS_CHANNEL_CMD_LATCH           'X'
#define XTREMIS_CHANNEL_CMD_POWER_OFF       '1'
#define XTREMIS_CHANNEL_CMD_POWER_ON        '0'
#define XTREMIS_CHANNEL_CMD_SET             'x'
#define XTREMIS_CHANNEL_CMD_SRB1_CONNECT    '1'
#define XTREMIS_CHANNEL_CMD_SRB1_DISCONNECT '0'
#define XTREMIS_CHANNEL_CMD_SRB2_CONNECT    '1'
#define XTREMIS_CHANNEL_CMD_SRB2_DISCONNECT '0'

/** Default Channel Settings */
#define XTREMIS_CHANNEL_DEFAULT_ALL_SET 'd'
#define XTREMIS_CHANNEL_DEFAULT_ALL_REPORT 'D'

/** LeadOff Impedance Commands */
#define XTREMIS_CHANNEL_IMPEDANCE_LATCH                'Z'
#define XTREMIS_CHANNEL_IMPEDANCE_SET                  'z'
#define XTREMIS_CHANNEL_IMPEDANCE_TEST_SIGNAL_APPLIED    '1'
#define XTREMIS_CHANNEL_IMPEDANCE_TEST_SIGNAL_APPLIED_NOT '0'

/** SD card Commands */
#define XTREMIS_SD_LOG_FOR_HOUR_1    'G'
#define XTREMIS_SD_LOG_FOR_HOUR_2    'H'
#define XTREMIS_SD_LOG_FOR_HOUR_4    'J'
#define XTREMIS_SD_LOG_FOR_HOUR_12   'K'
#define XTREMIS_SD_LOG_FOR_HOUR_24   'L'
#define XTREMIS_SD_LOG_FOR_MIN_5     'A'
#define XTREMIS_SD_LOG_FOR_MIN_15    'S'
#define XTREMIS_SD_LOG_FOR_MIN_30    'F'
#define XTREMIS_SD_LOG_FOR_SEC_14    'a'
#define XTREMIS_SD_LOG_STOP        'j'

/** Stream Data Commands */
#define XTREMIS_STREAM_START  'b'
#define XTREMIS_STREAM_STOP   's'

/** Miscellaneous */
#define XTREMIS_MISC_QUERY_REGISTER_SETTINGS '?'
#define XTREMIS_MISC_SOFT_RESET              'v'

/** 16 Channel Commands */
#define XTREMIS_CHANNEL_MAX_NUMBER_8    'c'
#define XTREMIS_CHANNEL_MAX_NUMBER_16   'C'

/** Set Packet Type */
#define XTREMIS_BOARD_MODE_SET '/'
#define XTREMIS_BOARD_MODE_DEFAULT '0'
#define XTREMIS_BOARD_MODE_DEBUG '1'
#define XTREMIS_BOARD_MODE_WIFI '2'
#define XTREMIS_BOARD_MODE_INPUT_ANALOG '3'
#define XTREMIS_BOARD_MODE_INPUT_DIGITAL '4'

/** Sync Clocks */
#define XTREMIS_TIME_SET '<'
#define XTREMIS_TIME_STOP '>'

/** Possible number of channels */
#define XTREMIS_NUMBER_OF_CHANNELS_DAISY 16
#define XTREMIS_NUMBER_OF_CHANNELS_DEFAULT 8

/** Helpful numbers */
#define XTREMIS_NUMBER_OF_BOARD_SETTINGS 1
#define XTREMIS_NUMBER_OF_CHANNEL_SETTINGS 6
#define XTREMIS_NUMBER_OF_LEAD_OFF_SETTINGS 2

/** Possible Sample Rates*/
#define XTREMIS_SAMPLE_RATE_125 125
#define XTREMIS_SAMPLE_RATE_250 250

/** Packet Size */
#define XTREMIS_PACKET_SIZE 33

/** Impedance Calculation Variables */
#define XTREMIS_LEAD_OFF_DRIVE_AMPS 0.000000006
#define XTREMIS_LEAD_OFF_FREQUENCY_HZ 31

/** Raw data packet types/codes */
#define XTREMIS_PACKET_TYPE_V3              0 // 0000
#define XTREMIS_PACKET_TYPE_TIME_SYNCED     1 // 0001
#define XTREMIS_PACKET_TYPE_TIME_SET        2 // 0010
#define XTREMIS_PACKET_TYPE_USER_DEFINED    3 // 0011
#define XTREMIS_PACKET_TYPE_RAW_AUX         4 // 0100

#define XTREMIS_TIME_OUT_MS_1 1
#define XTREMIS_TIME_OUT_MS_3 3

#define XTREMIS_NUMBER_OF_BYTES_SETTINGS_CHANNEL 9
#define XTREMIS_NUMBER_OF_BYTES_SETTINGS_LEAD_OFF 5

#define XTREMIS_NUMBER_OF_BYTES_AUX 6

#define XTREMIS_FIRMWARE_VERSION_V1 1
#define XTREMIS_FIRMWARE_VERSION_V2 1

// Triggers
#define NUM_TRIG_PINS 5
#define SETUP_TRIGGER_PINS '\\'

#endif