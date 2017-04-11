LOCAL_PATH := $(call my-dir)
 
 
include $(CLEAR_VARS)
LOCAL_MODULE := gpio_test
LOCAL_SRC_FILES := gpio.c
LOCAL_LDLIBS += -llog
LOCAL_CFLAGS += -DEXEC
include $(BUILD_EXECUTABLE)
 
include $(CLEAR_VARS)
LOCAL_MODULE := libgpio
LOCAL_SRC_FILES := gpio.c jni_gpio.c
LOCAL_LDLIBS += -llog
include $(BUILD_SHARED_LIBRARY)