#固定写法
LOCAL_PATH:=$(call my-dir)
#固定写法
include $(CLEAR_VARS)
#生成so名称
LOCAL_MODULE := Jnitest
LOCAL_SRC_FILES := com_develop_hua_jni_MainActivity.cpp
#固定写法
include $(BUILD_SHARED_LIBRARY)
