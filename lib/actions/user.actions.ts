'use server'

import { ID } from "node-appwrite";
import { createAdminClient, createSessionClient } from "../appwrite"
import { cookies } from "next/headers";
import { parseStringify } from "../utils";

export const signIn = async ({email, password}: signInProps) => {
    try{
        const { account } = await createAdminClient();

        const response = await account.createEmailPasswordSession(email, password)
    return parseStringify(response)
    }catch(error){
        
    }
}

export const signUp = async (userData: SignUpParams) => {
    try{
        const { account } = await createAdminClient();

        const newUser = await account.create(
            ID.unique(),
            userData.email,
            userData.password,
            `${userData.firstName} ${userData.lastName}`
        )
        const session = await account.createEmailPasswordSession(userData.email, userData.password)

        ;(await cookies()).set('appwrite-session', session.secret, {
            path: '/',
            httpOnly: true,
            sameSite: 'strict',
            secure: true
        })

        return parseStringify(newUser)
    }catch (error){
        console.log(error)
    }
}

export async function getLoggedInUser () {
    try{
        const { account} = await createSessionClient();
        return await account.get();
    }catch(error){
        return null
    }
}