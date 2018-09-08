package com.creative.domain.state


interface   BaseVS {

    class Loading : BaseVS{
        var type = 0
        companion object {
            fun getWithType(t:Int):Loading{
                var instance = Loading()
                instance.type = t
                return instance
            }
        }
    }

    class Error(val error:String) : BaseVS{
        var type = 0
        companion object {
            fun getWithType(error:String,t:Int):Error{
                var errorInstance = Error(error)
                errorInstance.type = t
                return errorInstance
            }
        }
    }

    class Empty : BaseVS{
        var type = 0
        companion object {
            fun getWithType(t:Int):Empty{
                var instance = Empty()
                instance.type = t
                return instance
            }
        }
    }
}