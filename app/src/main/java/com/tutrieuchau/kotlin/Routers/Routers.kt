package com.tutrieuchau.kotlin.Routers

import android.content.Context
import android.content.Intent
import com.tutrieuchau.kotlin.Controllers.Login
import com.tutrieuchau.kotlin.Controllers.Registration
import org.rekotlinrouter.Routable
import org.rekotlinrouter.RouteElementIdentifier
import org.rekotlinrouter.RoutingCompletionHandler

val registrationIdentifier: RouteElementIdentifier = "RegistrationIdentifier"
val loginIdentifier : RouteElementIdentifier = "LoginIdentifier"

class RoutableHelper{
    fun createLoginRoutable(context: Context): LoginRoutable{
        val loginIntent = Intent(context, Login::class.java)
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(loginIntent)
        return LoginRoutable(context)
    }
    fun createRegistrationRoutable(context: Context): RegistrationRoutable{
        val registrationIntent = Intent(context, Registration::class.java)
        registrationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(registrationIntent)
        return RegistrationRoutable(context)
    }
}

class RootRoutable( val context: Context): Routable{
    override fun changeRouteSegment(from: RouteElementIdentifier, to: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler): Routable {

    }

    override fun popRouteSegment(routeElementIdentifier: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler) {

    }

    override fun pushRouteSegment(routeElementIdentifier: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler): Routable {
        if(routeElementIdentifier == loginIdentifier){
            return LoginRoutable(context)
        }else if (routeElementIdentifier == registrationIdentifier){
            return RegistrationRoutable(context)
        }
        return  LoginRoutable(context)
    }
}
class RegistrationRoutable(val context: Context):Routable{
    override fun changeRouteSegment(from: RouteElementIdentifier, to: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler): Routable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun popRouteSegment(routeElementIdentifier: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pushRouteSegment(routeElementIdentifier: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler): Routable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
class LoginRoutable(val context: Context): Routable{
    override fun changeRouteSegment(from: RouteElementIdentifier, to: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler): Routable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun popRouteSegment(routeElementIdentifier: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pushRouteSegment(routeElementIdentifier: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler): Routable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}