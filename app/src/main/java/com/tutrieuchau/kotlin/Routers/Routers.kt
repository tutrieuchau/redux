package com.tutrieuchau.kotlin.Routers

import android.content.Context
import android.content.Intent
import android.util.Log
import com.tutrieuchau.kotlin.Controllers.Registration
import com.tutrieuchau.kotlin.Controllers.Welcome
import org.rekotlinrouter.Routable
import org.rekotlinrouter.RouteElementIdentifier
import org.rekotlinrouter.RoutingCompletionHandler

val registrationIdentifier: RouteElementIdentifier = "RegistrationIdentifier"
val loginIdentifier : RouteElementIdentifier = "LoginIdentifier"
val welcomeIdentifier : RouteElementIdentifier = "WelcomeIdentifier"

object RoutableHelper{
    fun createRegistrationRoutable(context: Context): RegistrationRoutable{
        val registrationIntent = Intent(context, Registration::class.java)
        registrationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(registrationIntent)
        return RegistrationRoutable(context)
    }
    fun createWelComeRoutable(context: Context): WelcomeRoutable {
        val welcomeIntent = Intent(context, Welcome::class.java)
        welcomeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(welcomeIntent)
        return WelcomeRoutable(context)
    }

}

class RootRoutable( val context: Context): Routable{
    override fun changeRouteSegment(from: RouteElementIdentifier, to: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler): Routable {
        return this
    }

    override fun popRouteSegment(routeElementIdentifier: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler) {
        Log.d("Pop","")
    }

    override fun pushRouteSegment(routeElementIdentifier: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler): Routable {
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
        TODO("not implemented")
    }
}
class LoginRoutable(val context: Context): Routable{
    override fun changeRouteSegment(from: RouteElementIdentifier, to: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler): Routable {
        return this
    }

    override fun popRouteSegment(routeElementIdentifier: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler) {
        Log.d("","")
    }

    override fun pushRouteSegment(routeElementIdentifier: RouteElementIdentifier, animated: Boolean, completionHandler: RoutingCompletionHandler): Routable {
        if(routeElementIdentifier == registrationIdentifier){
            return RoutableHelper.createRegistrationRoutable(context)
        }else if(routeElementIdentifier == welcomeIdentifier){
            return RoutableHelper.createWelComeRoutable(context)
        }
        return  this
    }
}
class WelcomeRoutable(val context: Context):Routable{
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