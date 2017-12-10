#
# Generated Makefile - do not edit!
#
# Edit the Makefile in the project folder instead (../Makefile). Each target
# has a -pre and a -post target defined where you can add customized code.
#
# This makefile implements configuration specific macros and targets.


# Include project Makefile
ifeq "${IGNORE_LOCAL}" "TRUE"
# do not include local makefile. User is passing all local related variables already
else
include Makefile
# Include makefile containing local settings
ifeq "$(wildcard nbproject/Makefile-local-default.mk)" "nbproject/Makefile-local-default.mk"
include nbproject/Makefile-local-default.mk
endif
endif

# Environment
MKDIR=mkdir -p
RM=rm -f 
MV=mv 
CP=cp 

# Macros
CND_CONF=default
ifeq ($(TYPE_IMAGE), DEBUG_RUN)
IMAGE_TYPE=debug
OUTPUT_SUFFIX=elf
DEBUGGABLE_SUFFIX=elf
FINAL_IMAGE=dist/${CND_CONF}/${IMAGE_TYPE}/XTREMIS_FIRMWARE.${IMAGE_TYPE}.${OUTPUT_SUFFIX}
else
IMAGE_TYPE=production
OUTPUT_SUFFIX=hex
DEBUGGABLE_SUFFIX=elf
FINAL_IMAGE=dist/${CND_CONF}/${IMAGE_TYPE}/XTREMIS_FIRMWARE.${IMAGE_TYPE}.${OUTPUT_SUFFIX}
endif

ifeq ($(COMPARE_BUILD), true)
COMPARISON_BUILD=-mafrlcsj
else
COMPARISON_BUILD=
endif

ifdef SUB_IMAGE_ADDRESS

else
SUB_IMAGE_ADDRESS_COMMAND=
endif

# Object Directory
OBJECTDIR=build/${CND_CONF}/${IMAGE_TYPE}

# Distribution Directory
DISTDIR=dist/${CND_CONF}/${IMAGE_TYPE}

# Source Files Quoted if spaced
SOURCEFILES_QUOTED_IF_SPACED=chipKIT-libraries/BoardLib/XTREMIS_Library.cpp chipKIT-libraries/DSPI/DSPI.cpp chipKIT-libraries/EEPROM/utility/Deeprom.c chipKIT-libraries/EEPROM/utility/flash.c chipKIT-libraries/EEPROM/EEPROM.cpp chipKIT-libraries/OBCI32_SD/utility/Sd2Card.cpp chipKIT-libraries/OBCI32_SD/utility/SdFile.cpp chipKIT-libraries/OBCI32_SD/utility/SdVolume.cpp chipKIT-libraries/OBCI32_SD/File.cpp chipKIT-libraries/OBCI32_SD/XTREMIS_SD.cpp source/DefaultBoard.cpp

# Object Files Quoted if spaced
OBJECTFILES_QUOTED_IF_SPACED=${OBJECTDIR}/chipKIT-libraries/BoardLib/XTREMIS_Library.o ${OBJECTDIR}/chipKIT-libraries/DSPI/DSPI.o ${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/Deeprom.o ${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/flash.o ${OBJECTDIR}/chipKIT-libraries/EEPROM/EEPROM.o ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/Sd2Card.o ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdFile.o ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdVolume.o ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/File.o ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/XTREMIS_SD.o ${OBJECTDIR}/source/DefaultBoard.o
POSSIBLE_DEPFILES=${OBJECTDIR}/chipKIT-libraries/BoardLib/XTREMIS_Library.o.d ${OBJECTDIR}/chipKIT-libraries/DSPI/DSPI.o.d ${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/Deeprom.o.d ${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/flash.o.d ${OBJECTDIR}/chipKIT-libraries/EEPROM/EEPROM.o.d ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/Sd2Card.o.d ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdFile.o.d ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdVolume.o.d ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/File.o.d ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/XTREMIS_SD.o.d ${OBJECTDIR}/source/DefaultBoard.o.d

# Object Files
OBJECTFILES=${OBJECTDIR}/chipKIT-libraries/BoardLib/XTREMIS_Library.o ${OBJECTDIR}/chipKIT-libraries/DSPI/DSPI.o ${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/Deeprom.o ${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/flash.o ${OBJECTDIR}/chipKIT-libraries/EEPROM/EEPROM.o ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/Sd2Card.o ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdFile.o ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdVolume.o ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/File.o ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/XTREMIS_SD.o ${OBJECTDIR}/source/DefaultBoard.o

# Source Files
SOURCEFILES=chipKIT-libraries/BoardLib/XTREMIS_Library.cpp chipKIT-libraries/DSPI/DSPI.cpp chipKIT-libraries/EEPROM/utility/Deeprom.c chipKIT-libraries/EEPROM/utility/flash.c chipKIT-libraries/EEPROM/EEPROM.cpp chipKIT-libraries/OBCI32_SD/utility/Sd2Card.cpp chipKIT-libraries/OBCI32_SD/utility/SdFile.cpp chipKIT-libraries/OBCI32_SD/utility/SdVolume.cpp chipKIT-libraries/OBCI32_SD/File.cpp chipKIT-libraries/OBCI32_SD/XTREMIS_SD.cpp source/DefaultBoard.cpp


CFLAGS=
ASFLAGS=
LDLIBSOPTIONS=

############# Tool locations ##########################################
# If you copy a project from one host to another, the path where the  #
# compiler is installed may be different.                             #
# If you open this project with MPLAB X in the new host, this         #
# makefile will be regenerated and the paths will be corrected.       #
#######################################################################
# fixDeps replaces a bunch of sed/cat/printf statements that slow down the build
FIXDEPS=fixDeps

.build-conf:  ${BUILD_SUBPROJECTS}
ifneq ($(INFORMATION_MESSAGE), )
	@echo $(INFORMATION_MESSAGE)
endif
	${MAKE}  -f nbproject/Makefile-default.mk dist/${CND_CONF}/${IMAGE_TYPE}/XTREMIS_FIRMWARE.${IMAGE_TYPE}.${OUTPUT_SUFFIX}

MP_PROCESSOR_OPTION=32MX250F128B
MP_LINKER_FILE_OPTION=
# ------------------------------------------------------------------------------------
# Rules for buildStep: assemble
ifeq ($(TYPE_IMAGE), DEBUG_RUN)
else
endif

# ------------------------------------------------------------------------------------
# Rules for buildStep: assembleWithPreprocess
ifeq ($(TYPE_IMAGE), DEBUG_RUN)
else
endif

# ------------------------------------------------------------------------------------
# Rules for buildStep: compile
ifeq ($(TYPE_IMAGE), DEBUG_RUN)
${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/Deeprom.o: chipKIT-libraries/EEPROM/utility/Deeprom.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} "${OBJECTDIR}/chipKIT-libraries/EEPROM/utility" 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/Deeprom.o.d 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/Deeprom.o 
	@${FIXDEPS} "${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/Deeprom.o.d" $(SILENT) -rsi ${MP_CC_DIR}../  -c ${MP_CPPC}  $(MP_EXTRA_CC_PRE) -g -D__DEBUG -DPK3OBPlatformTool=1  -fframe-base-loclist  -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"chipKIT-core" -I"chipKIT-libraries/BoardLib" -I"chipKIT-libraries/DSPI" -I"chipKIT-libraries/EEPROM" -I"chipKIT-libraries/EEPROM/utility" -I"chipKIT-libraries/OBCI32_SD" -I"chipKIT-libraries/OBCI32_SD/utility" -DF_CPU=40000000L -DARDUINO=10613 -D_BOARD_DP32_ -DMPIDEVER=16777998 -DMPIDE=150 -DIDE=Arduino -DXPRJ_default=default -D__CTYPE_NEWLIB -MMD -MF "${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/Deeprom.o.d" -o ${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/Deeprom.o chipKIT-libraries/EEPROM/utility/Deeprom.c    -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)  -DARDUINO_ARCH_PIC32 -mno-smart-io -ffunction-sections -fdata-sections -mdebugger -Wcast-align -fno-short-double -ftoplevel-reorder -MMD -G1024 -D_USE_USB_FOR_SERIAL_ -mnewlib-libc
	
${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/flash.o: chipKIT-libraries/EEPROM/utility/flash.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} "${OBJECTDIR}/chipKIT-libraries/EEPROM/utility" 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/flash.o.d 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/flash.o 
	@${FIXDEPS} "${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/flash.o.d" $(SILENT) -rsi ${MP_CC_DIR}../  -c ${MP_CPPC}  $(MP_EXTRA_CC_PRE) -g -D__DEBUG -DPK3OBPlatformTool=1  -fframe-base-loclist  -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"chipKIT-core" -I"chipKIT-libraries/BoardLib" -I"chipKIT-libraries/DSPI" -I"chipKIT-libraries/EEPROM" -I"chipKIT-libraries/EEPROM/utility" -I"chipKIT-libraries/OBCI32_SD" -I"chipKIT-libraries/OBCI32_SD/utility" -DF_CPU=40000000L -DARDUINO=10613 -D_BOARD_DP32_ -DMPIDEVER=16777998 -DMPIDE=150 -DIDE=Arduino -DXPRJ_default=default -D__CTYPE_NEWLIB -MMD -MF "${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/flash.o.d" -o ${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/flash.o chipKIT-libraries/EEPROM/utility/flash.c    -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)  -DARDUINO_ARCH_PIC32 -mno-smart-io -ffunction-sections -fdata-sections -mdebugger -Wcast-align -fno-short-double -ftoplevel-reorder -MMD -G1024 -D_USE_USB_FOR_SERIAL_ -mnewlib-libc
	
else
${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/Deeprom.o: chipKIT-libraries/EEPROM/utility/Deeprom.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} "${OBJECTDIR}/chipKIT-libraries/EEPROM/utility" 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/Deeprom.o.d 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/Deeprom.o 
	@${FIXDEPS} "${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/Deeprom.o.d" $(SILENT) -rsi ${MP_CC_DIR}../  -c ${MP_CPPC}  $(MP_EXTRA_CC_PRE)  -g -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"chipKIT-core" -I"chipKIT-libraries/BoardLib" -I"chipKIT-libraries/DSPI" -I"chipKIT-libraries/EEPROM" -I"chipKIT-libraries/EEPROM/utility" -I"chipKIT-libraries/OBCI32_SD" -I"chipKIT-libraries/OBCI32_SD/utility" -DF_CPU=40000000L -DARDUINO=10613 -D_BOARD_DP32_ -DMPIDEVER=16777998 -DMPIDE=150 -DIDE=Arduino -DXPRJ_default=default -D__CTYPE_NEWLIB -MMD -MF "${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/Deeprom.o.d" -o ${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/Deeprom.o chipKIT-libraries/EEPROM/utility/Deeprom.c    -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)  -DARDUINO_ARCH_PIC32 -mno-smart-io -ffunction-sections -fdata-sections -mdebugger -Wcast-align -fno-short-double -ftoplevel-reorder -MMD -G1024 -D_USE_USB_FOR_SERIAL_ -mnewlib-libc
	
${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/flash.o: chipKIT-libraries/EEPROM/utility/flash.c  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} "${OBJECTDIR}/chipKIT-libraries/EEPROM/utility" 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/flash.o.d 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/flash.o 
	@${FIXDEPS} "${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/flash.o.d" $(SILENT) -rsi ${MP_CC_DIR}../  -c ${MP_CPPC}  $(MP_EXTRA_CC_PRE)  -g -x c -c -mprocessor=$(MP_PROCESSOR_OPTION) -I"chipKIT-core" -I"chipKIT-libraries/BoardLib" -I"chipKIT-libraries/DSPI" -I"chipKIT-libraries/EEPROM" -I"chipKIT-libraries/EEPROM/utility" -I"chipKIT-libraries/OBCI32_SD" -I"chipKIT-libraries/OBCI32_SD/utility" -DF_CPU=40000000L -DARDUINO=10613 -D_BOARD_DP32_ -DMPIDEVER=16777998 -DMPIDE=150 -DIDE=Arduino -DXPRJ_default=default -D__CTYPE_NEWLIB -MMD -MF "${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/flash.o.d" -o ${OBJECTDIR}/chipKIT-libraries/EEPROM/utility/flash.o chipKIT-libraries/EEPROM/utility/flash.c    -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)  -DARDUINO_ARCH_PIC32 -mno-smart-io -ffunction-sections -fdata-sections -mdebugger -Wcast-align -fno-short-double -ftoplevel-reorder -MMD -G1024 -D_USE_USB_FOR_SERIAL_ -mnewlib-libc
	
endif

# ------------------------------------------------------------------------------------
# Rules for buildStep: compileCPP
ifeq ($(TYPE_IMAGE), DEBUG_RUN)
${OBJECTDIR}/chipKIT-libraries/BoardLib/XTREMIS_Library.o: chipKIT-libraries/BoardLib/XTREMIS_Library.cpp  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} "${OBJECTDIR}/chipKIT-libraries/BoardLib" 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/BoardLib/XTREMIS_Library.o.d 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/BoardLib/XTREMIS_Library.o 
	@${FIXDEPS} "${OBJECTDIR}/chipKIT-libraries/BoardLib/XTREMIS_Library.o.d" $(SILENT) -rsi ${MP_CC_DIR}../  -c ${MP_CPPC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -DPK3OBPlatformTool=1  -fframe-base-loclist  -x c++ -c -mprocessor=$(MP_PROCESSOR_OPTION)  -I"chipKIT-core" -I"chipKIT-libraries/BoardLib" -I"chipKIT-libraries/DSPI" -I"chipKIT-libraries/EEPROM" -I"chipKIT-libraries/EEPROM/utility" -I"chipKIT-libraries/OBCI32_SD" -I"chipKIT-libraries/OBCI32_SD/utility" -frtti -fno-exceptions -fno-check-new -fenforce-eh-specs -DF_CPU=40000000L -DARDUINO=10613 -D_BOARD_DP32_ -DMPIDEVER=16777998 -DMPIDE=150 -DIDE=Arduino -DXPRJ_default=default -D__CTYPE_NEWLIB -MMD -MF "${OBJECTDIR}/chipKIT-libraries/BoardLib/XTREMIS_Library.o.d" -o ${OBJECTDIR}/chipKIT-libraries/BoardLib/XTREMIS_Library.o chipKIT-libraries/BoardLib/XTREMIS_Library.cpp   -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)  -DARDUINO_ARCH_PIC32 -mno-smart-io -ffunction-sections -fdata-sections -mdebugger -Wcast-align -fno-short-double -ftoplevel-reorder -MMD -G1024 -D_USE_USB_FOR_SERIAL_ -mnewlib-libc -std=gnu++11
	
${OBJECTDIR}/chipKIT-libraries/DSPI/DSPI.o: chipKIT-libraries/DSPI/DSPI.cpp  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} "${OBJECTDIR}/chipKIT-libraries/DSPI" 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/DSPI/DSPI.o.d 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/DSPI/DSPI.o 
	@${FIXDEPS} "${OBJECTDIR}/chipKIT-libraries/DSPI/DSPI.o.d" $(SILENT) -rsi ${MP_CC_DIR}../  -c ${MP_CPPC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -DPK3OBPlatformTool=1  -fframe-base-loclist  -x c++ -c -mprocessor=$(MP_PROCESSOR_OPTION)  -I"chipKIT-core" -I"chipKIT-libraries/BoardLib" -I"chipKIT-libraries/DSPI" -I"chipKIT-libraries/EEPROM" -I"chipKIT-libraries/EEPROM/utility" -I"chipKIT-libraries/OBCI32_SD" -I"chipKIT-libraries/OBCI32_SD/utility" -frtti -fno-exceptions -fno-check-new -fenforce-eh-specs -DF_CPU=40000000L -DARDUINO=10613 -D_BOARD_DP32_ -DMPIDEVER=16777998 -DMPIDE=150 -DIDE=Arduino -DXPRJ_default=default -D__CTYPE_NEWLIB -MMD -MF "${OBJECTDIR}/chipKIT-libraries/DSPI/DSPI.o.d" -o ${OBJECTDIR}/chipKIT-libraries/DSPI/DSPI.o chipKIT-libraries/DSPI/DSPI.cpp   -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)  -DARDUINO_ARCH_PIC32 -mno-smart-io -ffunction-sections -fdata-sections -mdebugger -Wcast-align -fno-short-double -ftoplevel-reorder -MMD -G1024 -D_USE_USB_FOR_SERIAL_ -mnewlib-libc -std=gnu++11
	
${OBJECTDIR}/chipKIT-libraries/EEPROM/EEPROM.o: chipKIT-libraries/EEPROM/EEPROM.cpp  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} "${OBJECTDIR}/chipKIT-libraries/EEPROM" 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/EEPROM/EEPROM.o.d 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/EEPROM/EEPROM.o 
	@${FIXDEPS} "${OBJECTDIR}/chipKIT-libraries/EEPROM/EEPROM.o.d" $(SILENT) -rsi ${MP_CC_DIR}../  -c ${MP_CPPC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -DPK3OBPlatformTool=1  -fframe-base-loclist  -x c++ -c -mprocessor=$(MP_PROCESSOR_OPTION)  -I"chipKIT-core" -I"chipKIT-libraries/BoardLib" -I"chipKIT-libraries/DSPI" -I"chipKIT-libraries/EEPROM" -I"chipKIT-libraries/EEPROM/utility" -I"chipKIT-libraries/OBCI32_SD" -I"chipKIT-libraries/OBCI32_SD/utility" -frtti -fno-exceptions -fno-check-new -fenforce-eh-specs -DF_CPU=40000000L -DARDUINO=10613 -D_BOARD_DP32_ -DMPIDEVER=16777998 -DMPIDE=150 -DIDE=Arduino -DXPRJ_default=default -D__CTYPE_NEWLIB -MMD -MF "${OBJECTDIR}/chipKIT-libraries/EEPROM/EEPROM.o.d" -o ${OBJECTDIR}/chipKIT-libraries/EEPROM/EEPROM.o chipKIT-libraries/EEPROM/EEPROM.cpp   -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)  -DARDUINO_ARCH_PIC32 -mno-smart-io -ffunction-sections -fdata-sections -mdebugger -Wcast-align -fno-short-double -ftoplevel-reorder -MMD -G1024 -D_USE_USB_FOR_SERIAL_ -mnewlib-libc -std=gnu++11
	
${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/Sd2Card.o: chipKIT-libraries/OBCI32_SD/utility/Sd2Card.cpp  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility" 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/Sd2Card.o.d 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/Sd2Card.o 
	@${FIXDEPS} "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/Sd2Card.o.d" $(SILENT) -rsi ${MP_CC_DIR}../  -c ${MP_CPPC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -DPK3OBPlatformTool=1  -fframe-base-loclist  -x c++ -c -mprocessor=$(MP_PROCESSOR_OPTION)  -I"chipKIT-core" -I"chipKIT-libraries/BoardLib" -I"chipKIT-libraries/DSPI" -I"chipKIT-libraries/EEPROM" -I"chipKIT-libraries/EEPROM/utility" -I"chipKIT-libraries/OBCI32_SD" -I"chipKIT-libraries/OBCI32_SD/utility" -frtti -fno-exceptions -fno-check-new -fenforce-eh-specs -DF_CPU=40000000L -DARDUINO=10613 -D_BOARD_DP32_ -DMPIDEVER=16777998 -DMPIDE=150 -DIDE=Arduino -DXPRJ_default=default -D__CTYPE_NEWLIB -MMD -MF "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/Sd2Card.o.d" -o ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/Sd2Card.o chipKIT-libraries/OBCI32_SD/utility/Sd2Card.cpp   -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)  -DARDUINO_ARCH_PIC32 -mno-smart-io -ffunction-sections -fdata-sections -mdebugger -Wcast-align -fno-short-double -ftoplevel-reorder -MMD -G1024 -D_USE_USB_FOR_SERIAL_ -mnewlib-libc -std=gnu++11
	
${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdFile.o: chipKIT-libraries/OBCI32_SD/utility/SdFile.cpp  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility" 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdFile.o.d 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdFile.o 
	@${FIXDEPS} "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdFile.o.d" $(SILENT) -rsi ${MP_CC_DIR}../  -c ${MP_CPPC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -DPK3OBPlatformTool=1  -fframe-base-loclist  -x c++ -c -mprocessor=$(MP_PROCESSOR_OPTION)  -I"chipKIT-core" -I"chipKIT-libraries/BoardLib" -I"chipKIT-libraries/DSPI" -I"chipKIT-libraries/EEPROM" -I"chipKIT-libraries/EEPROM/utility" -I"chipKIT-libraries/OBCI32_SD" -I"chipKIT-libraries/OBCI32_SD/utility" -frtti -fno-exceptions -fno-check-new -fenforce-eh-specs -DF_CPU=40000000L -DARDUINO=10613 -D_BOARD_DP32_ -DMPIDEVER=16777998 -DMPIDE=150 -DIDE=Arduino -DXPRJ_default=default -D__CTYPE_NEWLIB -MMD -MF "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdFile.o.d" -o ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdFile.o chipKIT-libraries/OBCI32_SD/utility/SdFile.cpp   -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)  -DARDUINO_ARCH_PIC32 -mno-smart-io -ffunction-sections -fdata-sections -mdebugger -Wcast-align -fno-short-double -ftoplevel-reorder -MMD -G1024 -D_USE_USB_FOR_SERIAL_ -mnewlib-libc -std=gnu++11
	
${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdVolume.o: chipKIT-libraries/OBCI32_SD/utility/SdVolume.cpp  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility" 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdVolume.o.d 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdVolume.o 
	@${FIXDEPS} "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdVolume.o.d" $(SILENT) -rsi ${MP_CC_DIR}../  -c ${MP_CPPC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -DPK3OBPlatformTool=1  -fframe-base-loclist  -x c++ -c -mprocessor=$(MP_PROCESSOR_OPTION)  -I"chipKIT-core" -I"chipKIT-libraries/BoardLib" -I"chipKIT-libraries/DSPI" -I"chipKIT-libraries/EEPROM" -I"chipKIT-libraries/EEPROM/utility" -I"chipKIT-libraries/OBCI32_SD" -I"chipKIT-libraries/OBCI32_SD/utility" -frtti -fno-exceptions -fno-check-new -fenforce-eh-specs -DF_CPU=40000000L -DARDUINO=10613 -D_BOARD_DP32_ -DMPIDEVER=16777998 -DMPIDE=150 -DIDE=Arduino -DXPRJ_default=default -D__CTYPE_NEWLIB -MMD -MF "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdVolume.o.d" -o ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdVolume.o chipKIT-libraries/OBCI32_SD/utility/SdVolume.cpp   -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)  -DARDUINO_ARCH_PIC32 -mno-smart-io -ffunction-sections -fdata-sections -mdebugger -Wcast-align -fno-short-double -ftoplevel-reorder -MMD -G1024 -D_USE_USB_FOR_SERIAL_ -mnewlib-libc -std=gnu++11
	
${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/File.o: chipKIT-libraries/OBCI32_SD/File.cpp  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD" 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/File.o.d 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/File.o 
	@${FIXDEPS} "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/File.o.d" $(SILENT) -rsi ${MP_CC_DIR}../  -c ${MP_CPPC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -DPK3OBPlatformTool=1  -fframe-base-loclist  -x c++ -c -mprocessor=$(MP_PROCESSOR_OPTION)  -I"chipKIT-core" -I"chipKIT-libraries/BoardLib" -I"chipKIT-libraries/DSPI" -I"chipKIT-libraries/EEPROM" -I"chipKIT-libraries/EEPROM/utility" -I"chipKIT-libraries/OBCI32_SD" -I"chipKIT-libraries/OBCI32_SD/utility" -frtti -fno-exceptions -fno-check-new -fenforce-eh-specs -DF_CPU=40000000L -DARDUINO=10613 -D_BOARD_DP32_ -DMPIDEVER=16777998 -DMPIDE=150 -DIDE=Arduino -DXPRJ_default=default -D__CTYPE_NEWLIB -MMD -MF "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/File.o.d" -o ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/File.o chipKIT-libraries/OBCI32_SD/File.cpp   -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)  -DARDUINO_ARCH_PIC32 -mno-smart-io -ffunction-sections -fdata-sections -mdebugger -Wcast-align -fno-short-double -ftoplevel-reorder -MMD -G1024 -D_USE_USB_FOR_SERIAL_ -mnewlib-libc -std=gnu++11
	
${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/XTREMIS_SD.o: chipKIT-libraries/OBCI32_SD/XTREMIS_SD.cpp  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD" 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/XTREMIS_SD.o.d 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/XTREMIS_SD.o 
	@${FIXDEPS} "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/XTREMIS_SD.o.d" $(SILENT) -rsi ${MP_CC_DIR}../  -c ${MP_CPPC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -DPK3OBPlatformTool=1  -fframe-base-loclist  -x c++ -c -mprocessor=$(MP_PROCESSOR_OPTION)  -I"chipKIT-core" -I"chipKIT-libraries/BoardLib" -I"chipKIT-libraries/DSPI" -I"chipKIT-libraries/EEPROM" -I"chipKIT-libraries/EEPROM/utility" -I"chipKIT-libraries/OBCI32_SD" -I"chipKIT-libraries/OBCI32_SD/utility" -frtti -fno-exceptions -fno-check-new -fenforce-eh-specs -DF_CPU=40000000L -DARDUINO=10613 -D_BOARD_DP32_ -DMPIDEVER=16777998 -DMPIDE=150 -DIDE=Arduino -DXPRJ_default=default -D__CTYPE_NEWLIB -MMD -MF "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/XTREMIS_SD.o.d" -o ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/XTREMIS_SD.o chipKIT-libraries/OBCI32_SD/XTREMIS_SD.cpp   -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)  -DARDUINO_ARCH_PIC32 -mno-smart-io -ffunction-sections -fdata-sections -mdebugger -Wcast-align -fno-short-double -ftoplevel-reorder -MMD -G1024 -D_USE_USB_FOR_SERIAL_ -mnewlib-libc -std=gnu++11
	
${OBJECTDIR}/source/DefaultBoard.o: source/DefaultBoard.cpp  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} "${OBJECTDIR}/source" 
	@${RM} ${OBJECTDIR}/source/DefaultBoard.o.d 
	@${RM} ${OBJECTDIR}/source/DefaultBoard.o 
	@${FIXDEPS} "${OBJECTDIR}/source/DefaultBoard.o.d" $(SILENT) -rsi ${MP_CC_DIR}../  -c ${MP_CPPC} $(MP_EXTRA_CC_PRE) -g -D__DEBUG -DPK3OBPlatformTool=1  -fframe-base-loclist  -x c++ -c -mprocessor=$(MP_PROCESSOR_OPTION)  -I"chipKIT-core" -I"chipKIT-libraries/BoardLib" -I"chipKIT-libraries/DSPI" -I"chipKIT-libraries/EEPROM" -I"chipKIT-libraries/EEPROM/utility" -I"chipKIT-libraries/OBCI32_SD" -I"chipKIT-libraries/OBCI32_SD/utility" -frtti -fno-exceptions -fno-check-new -fenforce-eh-specs -DF_CPU=40000000L -DARDUINO=10613 -D_BOARD_DP32_ -DMPIDEVER=16777998 -DMPIDE=150 -DIDE=Arduino -DXPRJ_default=default -D__CTYPE_NEWLIB -MMD -MF "${OBJECTDIR}/source/DefaultBoard.o.d" -o ${OBJECTDIR}/source/DefaultBoard.o source/DefaultBoard.cpp   -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)  -DARDUINO_ARCH_PIC32 -mno-smart-io -ffunction-sections -fdata-sections -mdebugger -Wcast-align -fno-short-double -ftoplevel-reorder -MMD -G1024 -D_USE_USB_FOR_SERIAL_ -mnewlib-libc -std=gnu++11
	
else
${OBJECTDIR}/chipKIT-libraries/BoardLib/XTREMIS_Library.o: chipKIT-libraries/BoardLib/XTREMIS_Library.cpp  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} "${OBJECTDIR}/chipKIT-libraries/BoardLib" 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/BoardLib/XTREMIS_Library.o.d 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/BoardLib/XTREMIS_Library.o 
	@${FIXDEPS} "${OBJECTDIR}/chipKIT-libraries/BoardLib/XTREMIS_Library.o.d" $(SILENT) -rsi ${MP_CC_DIR}../  -c ${MP_CPPC} $(MP_EXTRA_CC_PRE)  -g -x c++ -c -mprocessor=$(MP_PROCESSOR_OPTION)  -I"chipKIT-core" -I"chipKIT-libraries/BoardLib" -I"chipKIT-libraries/DSPI" -I"chipKIT-libraries/EEPROM" -I"chipKIT-libraries/EEPROM/utility" -I"chipKIT-libraries/OBCI32_SD" -I"chipKIT-libraries/OBCI32_SD/utility" -frtti -fno-exceptions -fno-check-new -fenforce-eh-specs -DF_CPU=40000000L -DARDUINO=10613 -D_BOARD_DP32_ -DMPIDEVER=16777998 -DMPIDE=150 -DIDE=Arduino -DXPRJ_default=default -D__CTYPE_NEWLIB -MMD -MF "${OBJECTDIR}/chipKIT-libraries/BoardLib/XTREMIS_Library.o.d" -o ${OBJECTDIR}/chipKIT-libraries/BoardLib/XTREMIS_Library.o chipKIT-libraries/BoardLib/XTREMIS_Library.cpp   -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)  -DARDUINO_ARCH_PIC32 -mno-smart-io -ffunction-sections -fdata-sections -mdebugger -Wcast-align -fno-short-double -ftoplevel-reorder -MMD -G1024 -D_USE_USB_FOR_SERIAL_ -mnewlib-libc -std=gnu++11
	
${OBJECTDIR}/chipKIT-libraries/DSPI/DSPI.o: chipKIT-libraries/DSPI/DSPI.cpp  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} "${OBJECTDIR}/chipKIT-libraries/DSPI" 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/DSPI/DSPI.o.d 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/DSPI/DSPI.o 
	@${FIXDEPS} "${OBJECTDIR}/chipKIT-libraries/DSPI/DSPI.o.d" $(SILENT) -rsi ${MP_CC_DIR}../  -c ${MP_CPPC} $(MP_EXTRA_CC_PRE)  -g -x c++ -c -mprocessor=$(MP_PROCESSOR_OPTION)  -I"chipKIT-core" -I"chipKIT-libraries/BoardLib" -I"chipKIT-libraries/DSPI" -I"chipKIT-libraries/EEPROM" -I"chipKIT-libraries/EEPROM/utility" -I"chipKIT-libraries/OBCI32_SD" -I"chipKIT-libraries/OBCI32_SD/utility" -frtti -fno-exceptions -fno-check-new -fenforce-eh-specs -DF_CPU=40000000L -DARDUINO=10613 -D_BOARD_DP32_ -DMPIDEVER=16777998 -DMPIDE=150 -DIDE=Arduino -DXPRJ_default=default -D__CTYPE_NEWLIB -MMD -MF "${OBJECTDIR}/chipKIT-libraries/DSPI/DSPI.o.d" -o ${OBJECTDIR}/chipKIT-libraries/DSPI/DSPI.o chipKIT-libraries/DSPI/DSPI.cpp   -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)  -DARDUINO_ARCH_PIC32 -mno-smart-io -ffunction-sections -fdata-sections -mdebugger -Wcast-align -fno-short-double -ftoplevel-reorder -MMD -G1024 -D_USE_USB_FOR_SERIAL_ -mnewlib-libc -std=gnu++11
	
${OBJECTDIR}/chipKIT-libraries/EEPROM/EEPROM.o: chipKIT-libraries/EEPROM/EEPROM.cpp  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} "${OBJECTDIR}/chipKIT-libraries/EEPROM" 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/EEPROM/EEPROM.o.d 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/EEPROM/EEPROM.o 
	@${FIXDEPS} "${OBJECTDIR}/chipKIT-libraries/EEPROM/EEPROM.o.d" $(SILENT) -rsi ${MP_CC_DIR}../  -c ${MP_CPPC} $(MP_EXTRA_CC_PRE)  -g -x c++ -c -mprocessor=$(MP_PROCESSOR_OPTION)  -I"chipKIT-core" -I"chipKIT-libraries/BoardLib" -I"chipKIT-libraries/DSPI" -I"chipKIT-libraries/EEPROM" -I"chipKIT-libraries/EEPROM/utility" -I"chipKIT-libraries/OBCI32_SD" -I"chipKIT-libraries/OBCI32_SD/utility" -frtti -fno-exceptions -fno-check-new -fenforce-eh-specs -DF_CPU=40000000L -DARDUINO=10613 -D_BOARD_DP32_ -DMPIDEVER=16777998 -DMPIDE=150 -DIDE=Arduino -DXPRJ_default=default -D__CTYPE_NEWLIB -MMD -MF "${OBJECTDIR}/chipKIT-libraries/EEPROM/EEPROM.o.d" -o ${OBJECTDIR}/chipKIT-libraries/EEPROM/EEPROM.o chipKIT-libraries/EEPROM/EEPROM.cpp   -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)  -DARDUINO_ARCH_PIC32 -mno-smart-io -ffunction-sections -fdata-sections -mdebugger -Wcast-align -fno-short-double -ftoplevel-reorder -MMD -G1024 -D_USE_USB_FOR_SERIAL_ -mnewlib-libc -std=gnu++11
	
${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/Sd2Card.o: chipKIT-libraries/OBCI32_SD/utility/Sd2Card.cpp  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility" 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/Sd2Card.o.d 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/Sd2Card.o 
	@${FIXDEPS} "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/Sd2Card.o.d" $(SILENT) -rsi ${MP_CC_DIR}../  -c ${MP_CPPC} $(MP_EXTRA_CC_PRE)  -g -x c++ -c -mprocessor=$(MP_PROCESSOR_OPTION)  -I"chipKIT-core" -I"chipKIT-libraries/BoardLib" -I"chipKIT-libraries/DSPI" -I"chipKIT-libraries/EEPROM" -I"chipKIT-libraries/EEPROM/utility" -I"chipKIT-libraries/OBCI32_SD" -I"chipKIT-libraries/OBCI32_SD/utility" -frtti -fno-exceptions -fno-check-new -fenforce-eh-specs -DF_CPU=40000000L -DARDUINO=10613 -D_BOARD_DP32_ -DMPIDEVER=16777998 -DMPIDE=150 -DIDE=Arduino -DXPRJ_default=default -D__CTYPE_NEWLIB -MMD -MF "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/Sd2Card.o.d" -o ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/Sd2Card.o chipKIT-libraries/OBCI32_SD/utility/Sd2Card.cpp   -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)  -DARDUINO_ARCH_PIC32 -mno-smart-io -ffunction-sections -fdata-sections -mdebugger -Wcast-align -fno-short-double -ftoplevel-reorder -MMD -G1024 -D_USE_USB_FOR_SERIAL_ -mnewlib-libc -std=gnu++11
	
${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdFile.o: chipKIT-libraries/OBCI32_SD/utility/SdFile.cpp  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility" 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdFile.o.d 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdFile.o 
	@${FIXDEPS} "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdFile.o.d" $(SILENT) -rsi ${MP_CC_DIR}../  -c ${MP_CPPC} $(MP_EXTRA_CC_PRE)  -g -x c++ -c -mprocessor=$(MP_PROCESSOR_OPTION)  -I"chipKIT-core" -I"chipKIT-libraries/BoardLib" -I"chipKIT-libraries/DSPI" -I"chipKIT-libraries/EEPROM" -I"chipKIT-libraries/EEPROM/utility" -I"chipKIT-libraries/OBCI32_SD" -I"chipKIT-libraries/OBCI32_SD/utility" -frtti -fno-exceptions -fno-check-new -fenforce-eh-specs -DF_CPU=40000000L -DARDUINO=10613 -D_BOARD_DP32_ -DMPIDEVER=16777998 -DMPIDE=150 -DIDE=Arduino -DXPRJ_default=default -D__CTYPE_NEWLIB -MMD -MF "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdFile.o.d" -o ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdFile.o chipKIT-libraries/OBCI32_SD/utility/SdFile.cpp   -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)  -DARDUINO_ARCH_PIC32 -mno-smart-io -ffunction-sections -fdata-sections -mdebugger -Wcast-align -fno-short-double -ftoplevel-reorder -MMD -G1024 -D_USE_USB_FOR_SERIAL_ -mnewlib-libc -std=gnu++11
	
${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdVolume.o: chipKIT-libraries/OBCI32_SD/utility/SdVolume.cpp  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility" 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdVolume.o.d 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdVolume.o 
	@${FIXDEPS} "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdVolume.o.d" $(SILENT) -rsi ${MP_CC_DIR}../  -c ${MP_CPPC} $(MP_EXTRA_CC_PRE)  -g -x c++ -c -mprocessor=$(MP_PROCESSOR_OPTION)  -I"chipKIT-core" -I"chipKIT-libraries/BoardLib" -I"chipKIT-libraries/DSPI" -I"chipKIT-libraries/EEPROM" -I"chipKIT-libraries/EEPROM/utility" -I"chipKIT-libraries/OBCI32_SD" -I"chipKIT-libraries/OBCI32_SD/utility" -frtti -fno-exceptions -fno-check-new -fenforce-eh-specs -DF_CPU=40000000L -DARDUINO=10613 -D_BOARD_DP32_ -DMPIDEVER=16777998 -DMPIDE=150 -DIDE=Arduino -DXPRJ_default=default -D__CTYPE_NEWLIB -MMD -MF "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdVolume.o.d" -o ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/utility/SdVolume.o chipKIT-libraries/OBCI32_SD/utility/SdVolume.cpp   -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)  -DARDUINO_ARCH_PIC32 -mno-smart-io -ffunction-sections -fdata-sections -mdebugger -Wcast-align -fno-short-double -ftoplevel-reorder -MMD -G1024 -D_USE_USB_FOR_SERIAL_ -mnewlib-libc -std=gnu++11
	
${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/File.o: chipKIT-libraries/OBCI32_SD/File.cpp  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD" 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/File.o.d 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/File.o 
	@${FIXDEPS} "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/File.o.d" $(SILENT) -rsi ${MP_CC_DIR}../  -c ${MP_CPPC} $(MP_EXTRA_CC_PRE)  -g -x c++ -c -mprocessor=$(MP_PROCESSOR_OPTION)  -I"chipKIT-core" -I"chipKIT-libraries/BoardLib" -I"chipKIT-libraries/DSPI" -I"chipKIT-libraries/EEPROM" -I"chipKIT-libraries/EEPROM/utility" -I"chipKIT-libraries/OBCI32_SD" -I"chipKIT-libraries/OBCI32_SD/utility" -frtti -fno-exceptions -fno-check-new -fenforce-eh-specs -DF_CPU=40000000L -DARDUINO=10613 -D_BOARD_DP32_ -DMPIDEVER=16777998 -DMPIDE=150 -DIDE=Arduino -DXPRJ_default=default -D__CTYPE_NEWLIB -MMD -MF "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/File.o.d" -o ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/File.o chipKIT-libraries/OBCI32_SD/File.cpp   -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)  -DARDUINO_ARCH_PIC32 -mno-smart-io -ffunction-sections -fdata-sections -mdebugger -Wcast-align -fno-short-double -ftoplevel-reorder -MMD -G1024 -D_USE_USB_FOR_SERIAL_ -mnewlib-libc -std=gnu++11
	
${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/XTREMIS_SD.o: chipKIT-libraries/OBCI32_SD/XTREMIS_SD.cpp  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD" 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/XTREMIS_SD.o.d 
	@${RM} ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/XTREMIS_SD.o 
	@${FIXDEPS} "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/XTREMIS_SD.o.d" $(SILENT) -rsi ${MP_CC_DIR}../  -c ${MP_CPPC} $(MP_EXTRA_CC_PRE)  -g -x c++ -c -mprocessor=$(MP_PROCESSOR_OPTION)  -I"chipKIT-core" -I"chipKIT-libraries/BoardLib" -I"chipKIT-libraries/DSPI" -I"chipKIT-libraries/EEPROM" -I"chipKIT-libraries/EEPROM/utility" -I"chipKIT-libraries/OBCI32_SD" -I"chipKIT-libraries/OBCI32_SD/utility" -frtti -fno-exceptions -fno-check-new -fenforce-eh-specs -DF_CPU=40000000L -DARDUINO=10613 -D_BOARD_DP32_ -DMPIDEVER=16777998 -DMPIDE=150 -DIDE=Arduino -DXPRJ_default=default -D__CTYPE_NEWLIB -MMD -MF "${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/XTREMIS_SD.o.d" -o ${OBJECTDIR}/chipKIT-libraries/OBCI32_SD/XTREMIS_SD.o chipKIT-libraries/OBCI32_SD/XTREMIS_SD.cpp   -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)  -DARDUINO_ARCH_PIC32 -mno-smart-io -ffunction-sections -fdata-sections -mdebugger -Wcast-align -fno-short-double -ftoplevel-reorder -MMD -G1024 -D_USE_USB_FOR_SERIAL_ -mnewlib-libc -std=gnu++11
	
${OBJECTDIR}/source/DefaultBoard.o: source/DefaultBoard.cpp  nbproject/Makefile-${CND_CONF}.mk
	@${MKDIR} "${OBJECTDIR}/source" 
	@${RM} ${OBJECTDIR}/source/DefaultBoard.o.d 
	@${RM} ${OBJECTDIR}/source/DefaultBoard.o 
	@${FIXDEPS} "${OBJECTDIR}/source/DefaultBoard.o.d" $(SILENT) -rsi ${MP_CC_DIR}../  -c ${MP_CPPC} $(MP_EXTRA_CC_PRE)  -g -x c++ -c -mprocessor=$(MP_PROCESSOR_OPTION)  -I"chipKIT-core" -I"chipKIT-libraries/BoardLib" -I"chipKIT-libraries/DSPI" -I"chipKIT-libraries/EEPROM" -I"chipKIT-libraries/EEPROM/utility" -I"chipKIT-libraries/OBCI32_SD" -I"chipKIT-libraries/OBCI32_SD/utility" -frtti -fno-exceptions -fno-check-new -fenforce-eh-specs -DF_CPU=40000000L -DARDUINO=10613 -D_BOARD_DP32_ -DMPIDEVER=16777998 -DMPIDE=150 -DIDE=Arduino -DXPRJ_default=default -D__CTYPE_NEWLIB -MMD -MF "${OBJECTDIR}/source/DefaultBoard.o.d" -o ${OBJECTDIR}/source/DefaultBoard.o source/DefaultBoard.cpp   -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)  -DARDUINO_ARCH_PIC32 -mno-smart-io -ffunction-sections -fdata-sections -mdebugger -Wcast-align -fno-short-double -ftoplevel-reorder -MMD -G1024 -D_USE_USB_FOR_SERIAL_ -mnewlib-libc -std=gnu++11
	
endif

# ------------------------------------------------------------------------------------
# Rules for buildStep: link
ifeq ($(TYPE_IMAGE), DEBUG_RUN)
dist/${CND_CONF}/${IMAGE_TYPE}/XTREMIS_FIRMWARE.${IMAGE_TYPE}.${OUTPUT_SUFFIX}: ${OBJECTFILES}  nbproject/Makefile-${CND_CONF}.mk    
	@${MKDIR} dist/${CND_CONF}/${IMAGE_TYPE} 
	${MP_CPPC} $(MP_EXTRA_LD_PRE) -g -mdebugger -DPK3OBPlatformTool=1 -mprocessor=$(MP_PROCESSOR_OPTION) -mdebugger -mno-peripheral-libs -nostartfiles -mnewlib-libc -T"chipKIT-core/chipKIT-application-32MX250F128.ld" -T"chipKIT-core/chipKIT-application-COMMON.ld" -o dist/${CND_CONF}/${IMAGE_TYPE}/XTREMIS_FIRMWARE.${IMAGE_TYPE}.${OUTPUT_SUFFIX} ${OBJECTFILES_QUOTED_IF_SPACED}          -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)   -mreserve=data@0x0:0x1FC -mreserve=boot@0x1FC00490:0x1FC00BEF  -Wl,--defsym=__MPLAB_BUILD=1$(MP_EXTRA_LD_POST)$(MP_LINKER_FILE_OPTION),--defsym=__MPLAB_DEBUG=1,--defsym=__DEBUG=1,-D=__DEBUG_D,--defsym=PK3OBPlatformTool=1,--gc-sections,--no-code-in-dinit,--no-dinit-in-serial-mem,-Map="${DISTDIR}/${PROJECTNAME}.${IMAGE_TYPE}.map",--memorysummary,dist/${CND_CONF}/${IMAGE_TYPE}/memoryfile.xml,-LchipKIT-core,-lCore
	
else
dist/${CND_CONF}/${IMAGE_TYPE}/XTREMIS_FIRMWARE.${IMAGE_TYPE}.${OUTPUT_SUFFIX}: ${OBJECTFILES}  nbproject/Makefile-${CND_CONF}.mk    chipKIT-core/UDB32-MX2-DIP.hex
	@${MKDIR} dist/${CND_CONF}/${IMAGE_TYPE} 
	${MP_CPPC} $(MP_EXTRA_LD_PRE)  -mprocessor=$(MP_PROCESSOR_OPTION) -mdebugger -mno-peripheral-libs -nostartfiles -mnewlib-libc -T"chipKIT-core/chipKIT-application-32MX250F128.ld" -T"chipKIT-core/chipKIT-application-COMMON.ld" -o dist/${CND_CONF}/${IMAGE_TYPE}/XTREMIS_FIRMWARE.${IMAGE_TYPE}.${DEBUGGABLE_SUFFIX} ${OBJECTFILES_QUOTED_IF_SPACED}          -DXPRJ_default=$(CND_CONF)  $(COMPARISON_BUILD)  -Wl,--defsym=__MPLAB_BUILD=1$(MP_EXTRA_LD_POST)$(MP_LINKER_FILE_OPTION),--gc-sections,--no-code-in-dinit,--no-dinit-in-serial-mem,-Map="${DISTDIR}/${PROJECTNAME}.${IMAGE_TYPE}.map",--memorysummary,dist/${CND_CONF}/${IMAGE_TYPE}/memoryfile.xml,-LchipKIT-core,-lCore
	${MP_CC_DIR}/xc32-bin2hex dist/${CND_CONF}/${IMAGE_TYPE}/XTREMIS_FIRMWARE.${IMAGE_TYPE}.${DEBUGGABLE_SUFFIX} 
	@echo "Creating unified hex file"
	@"/Applications/microchip/mplabx/v4.05/mplab_ide.app/Contents/Resources/mplab_ide/platform/../mplab_ide/modules/../../bin/hexmate" --edf="/Applications/microchip/mplabx/v4.05/mplab_ide.app/Contents/Resources/mplab_ide/platform/../mplab_ide/modules/../../dat/en_msgs.txt" dist/${CND_CONF}/${IMAGE_TYPE}/XTREMIS_FIRMWARE.${IMAGE_TYPE}.hex chipKIT-core/UDB32-MX2-DIP.hex -odist/${CND_CONF}/production/XTREMIS_FIRMWARE.production.unified.hex

endif


# Subprojects
.build-subprojects:


# Subprojects
.clean-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r build/default
	${RM} -r dist/default

# Enable dependency checking
.dep.inc: .depcheck-impl

DEPFILES=$(shell "${PATH_TO_IDE_BIN}"mplabwildcard ${POSSIBLE_DEPFILES})
ifneq (${DEPFILES},)
include ${DEPFILES}
endif
