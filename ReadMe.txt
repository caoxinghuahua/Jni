jni的两种实现方式：
一.cmake
(1)新建一个Project,项目类型选择Native C++
   C++Standard 选择默认的ToolsChain default
(2)声明native方法名称,加载native库,生成的native库文件会自动加上lib前缀

(3)在main 下的cpp目录会自动生成需要的.cpp文件
   实现相应的方法即可
(4)在app的build.gradle文件下会生成如下代码
android {
    defaultConfig {
        externalNativeBuild {
            cmake {
                cppFlags ""
            }
        }
    }
    externalNativeBuild {//此处配置可能会因gradle版本不同而有差异
        cmake {
            path "src/main/cpp/CMakeLists.txt"
        }
    }
}
(5)执行make project命令
   会在/app/build/intermediates/cmake/debug/obj/目录下生成相应架构下的so文件。
   将相应的so目录拷贝到需要使用的项目lib目录，加载so库即可使用。






二.ndk-build

(1)声明native方法名称,加载native库,生成的native库文件会自动加上lib前缀
(2)切换到声明native方法的类所在目录，执行javah -jni com.develop.hua.jni.MainActivity
   在当前目录下会生成 com.develop.hua.jni.MainActivity.h的头文件
(3)右键app选择Folder生成JNI Folder
   会在main下生成jni目录
(4)将生成的native头文件剪切到jni目录下,修改文件后缀为.cpp
(5)右击jni > New > File
  创建空的Android.mk文件
  mk文件的内容如下：

  #固定写法
  LOCAL_PATH:=$(call my-dir)
  #固定写法
  include $(CLEAR_VARS)
  #生成so名称
  LOCAL_MODULE := JniHelper
  LOCAL_SRC_FILES := com_hai_ndkbuild_MainActivity.cpp
  #固定写法
  include $(BUILD_SHARED_LIBRARY)
（6）接下来右击app module
    选择link C++ Project with gradle =>build System选择ndk-build,Project Path选择Android.mk的路径
    ok之后app module的build.gradle文件自动新增externalNativeBuild配置：

    android {
        sourceSets { main { jni.srcDirs = ['src/main/jni', 'src/main/jni/'] } }
        externalNativeBuild {//此处配置可能会因gradle版本不同而有差异
            ndkBuild {
                path file('src/main/cpp/Android.mk')
            }
        }
    }
 (7)实现相应的native方法。
   执行make project命令
   会在/app/build/intermediates/ndkBuild/debug/obj/目录下生成相应架构下的so文件。
