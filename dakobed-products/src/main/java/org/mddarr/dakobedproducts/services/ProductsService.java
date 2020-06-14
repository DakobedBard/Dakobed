package org.mddarr.dakobedproducts.services;

import com.amazonaws.auth.AWSCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {

    @Autowired
    AWSCredentialsProvider awsCredentialsProvider;

}
