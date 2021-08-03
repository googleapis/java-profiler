/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.devtools.cloudprofiler.v2;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.devtools.cloudprofiler.v2.stub.ProfilerServiceStub;
import com.google.devtools.cloudprofiler.v2.stub.ProfilerServiceStubSettings;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Generated;

// AUTO-GENERATED DOCUMENTATION AND CLASS.
/**
 * Service Description: Manage the collection of continuous profiling data provided by profiling
 * agents running in the cloud or by an offline provider of profiling data.
 *
 * <p>General guidelines:
 *
 * <ul>
 *   <li> Profiles for a single deployment must be created in ascending time order.
 *   <li> Profiles can be created in either online or offline mode, see below.
 * </ul>
 *
 * <p>This class provides the ability to make remote calls to the backing service through method
 * calls that map to API methods. Sample code to get started:
 *
 * <pre>{@code
 * try (ProfilerServiceClient profilerServiceClient = ProfilerServiceClient.create()) {
 *   CreateProfileRequest request =
 *       CreateProfileRequest.newBuilder()
 *           .setParent("parent-995424086")
 *           .setDeployment(Deployment.newBuilder().build())
 *           .addAllProfileType(new ArrayList<ProfileType>())
 *           .build();
 *   Profile response = profilerServiceClient.createProfile(request);
 * }
 * }</pre>
 *
 * <p>Note: close() needs to be called on the ProfilerServiceClient object to clean up resources
 * such as threads. In the example above, try-with-resources is used, which automatically calls
 * close().
 *
 * <p>The surface of this class includes several types of Java methods for each of the API's
 * methods:
 *
 * <ol>
 *   <li> A "flattened" method. With this type of method, the fields of the request type have been
 *       converted into function parameters. It may be the case that not all fields are available as
 *       parameters, and not every API method will have a flattened method entry point.
 *   <li> A "request object" method. This type of method only takes one parameter, a request object,
 *       which must be constructed before the call. Not every API method will have a request object
 *       method.
 *   <li> A "callable" method. This type of method takes no parameters and returns an immutable API
 *       callable object, which can be used to initiate calls to the service.
 * </ol>
 *
 * <p>See the individual methods for example code.
 *
 * <p>Many parameters require resource names to be formatted in a particular way. To assist with
 * these names, this class includes a format method for each type of name, and additionally a parse
 * method to extract the individual identifiers contained within names that are returned.
 *
 * <p>This class can be customized by passing in a custom instance of ProfilerServiceSettings to
 * create(). For example:
 *
 * <p>To customize credentials:
 *
 * <pre>{@code
 * ProfilerServiceSettings profilerServiceSettings =
 *     ProfilerServiceSettings.newBuilder()
 *         .setCredentialsProvider(FixedCredentialsProvider.create(myCredentials))
 *         .build();
 * ProfilerServiceClient profilerServiceClient =
 *     ProfilerServiceClient.create(profilerServiceSettings);
 * }</pre>
 *
 * <p>To customize the endpoint:
 *
 * <pre>{@code
 * ProfilerServiceSettings profilerServiceSettings =
 *     ProfilerServiceSettings.newBuilder().setEndpoint(myEndpoint).build();
 * ProfilerServiceClient profilerServiceClient =
 *     ProfilerServiceClient.create(profilerServiceSettings);
 * }</pre>
 *
 * <p>Please refer to the GitHub repository's samples for more quickstart code snippets.
 */
@Generated("by gapic-generator-java")
public class ProfilerServiceClient implements BackgroundResource {
  private final ProfilerServiceSettings settings;
  private final ProfilerServiceStub stub;

  /** Constructs an instance of ProfilerServiceClient with default settings. */
  public static final ProfilerServiceClient create() throws IOException {
    return create(ProfilerServiceSettings.newBuilder().build());
  }

  /**
   * Constructs an instance of ProfilerServiceClient, using the given settings. The channels are
   * created based on the settings passed in, or defaults for any settings that are not set.
   */
  public static final ProfilerServiceClient create(ProfilerServiceSettings settings)
      throws IOException {
    return new ProfilerServiceClient(settings);
  }

  /**
   * Constructs an instance of ProfilerServiceClient, using the given stub for making calls. This is
   * for advanced usage - prefer using create(ProfilerServiceSettings).
   */
  @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
  public static final ProfilerServiceClient create(ProfilerServiceStub stub) {
    return new ProfilerServiceClient(stub);
  }

  /**
   * Constructs an instance of ProfilerServiceClient, using the given settings. This is protected so
   * that it is easy to make a subclass, but otherwise, the static factory methods should be
   * preferred.
   */
  protected ProfilerServiceClient(ProfilerServiceSettings settings) throws IOException {
    this.settings = settings;
    this.stub = ((ProfilerServiceStubSettings) settings.getStubSettings()).createStub();
  }

  @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
  protected ProfilerServiceClient(ProfilerServiceStub stub) {
    this.settings = null;
    this.stub = stub;
  }

  public final ProfilerServiceSettings getSettings() {
    return settings;
  }

  @BetaApi("A restructuring of stub classes is planned, so this may break in the future")
  public ProfilerServiceStub getStub() {
    return stub;
  }

  // AUTO-GENERATED DOCUMENTATION AND METHOD.
  /**
   * CreateProfile creates a new profile resource in the online mode.
   *
   * <p>The server ensures that the new profiles are created at a constant rate per deployment, so
   * the creation request may hang for some time until the next profile session is available.
   *
   * <p>The request may fail with ABORTED error if the creation is not available within ~1m, the
   * response will indicate the duration of the backoff the client should take before attempting
   * creating a profile again. The backoff duration is returned in google.rpc.RetryInfo extension on
   * the response status. To a gRPC client, the extension will be return as a binary-serialized
   * proto in the trailing metadata item named "google.rpc.retryinfo-bin".
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * try (ProfilerServiceClient profilerServiceClient = ProfilerServiceClient.create()) {
   *   CreateProfileRequest request =
   *       CreateProfileRequest.newBuilder()
   *           .setParent("parent-995424086")
   *           .setDeployment(Deployment.newBuilder().build())
   *           .addAllProfileType(new ArrayList<ProfileType>())
   *           .build();
   *   Profile response = profilerServiceClient.createProfile(request);
   * }
   * }</pre>
   *
   * @param request The request object containing all of the parameters for the API call.
   * @throws com.google.api.gax.rpc.ApiException if the remote call fails
   */
  public final Profile createProfile(CreateProfileRequest request) {
    return createProfileCallable().call(request);
  }

  // AUTO-GENERATED DOCUMENTATION AND METHOD.
  /**
   * CreateProfile creates a new profile resource in the online mode.
   *
   * <p>The server ensures that the new profiles are created at a constant rate per deployment, so
   * the creation request may hang for some time until the next profile session is available.
   *
   * <p>The request may fail with ABORTED error if the creation is not available within ~1m, the
   * response will indicate the duration of the backoff the client should take before attempting
   * creating a profile again. The backoff duration is returned in google.rpc.RetryInfo extension on
   * the response status. To a gRPC client, the extension will be return as a binary-serialized
   * proto in the trailing metadata item named "google.rpc.retryinfo-bin".
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * try (ProfilerServiceClient profilerServiceClient = ProfilerServiceClient.create()) {
   *   CreateProfileRequest request =
   *       CreateProfileRequest.newBuilder()
   *           .setParent("parent-995424086")
   *           .setDeployment(Deployment.newBuilder().build())
   *           .addAllProfileType(new ArrayList<ProfileType>())
   *           .build();
   *   ApiFuture<Profile> future = profilerServiceClient.createProfileCallable().futureCall(request);
   *   // Do something.
   *   Profile response = future.get();
   * }
   * }</pre>
   */
  public final UnaryCallable<CreateProfileRequest, Profile> createProfileCallable() {
    return stub.createProfileCallable();
  }

  // AUTO-GENERATED DOCUMENTATION AND METHOD.
  /**
   * CreateOfflineProfile creates a new profile resource in the offline mode. The client provides
   * the profile to create along with the profile bytes, the server records it.
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * try (ProfilerServiceClient profilerServiceClient = ProfilerServiceClient.create()) {
   *   CreateOfflineProfileRequest request =
   *       CreateOfflineProfileRequest.newBuilder()
   *           .setParent("parent-995424086")
   *           .setProfile(Profile.newBuilder().build())
   *           .build();
   *   Profile response = profilerServiceClient.createOfflineProfile(request);
   * }
   * }</pre>
   *
   * @param request The request object containing all of the parameters for the API call.
   * @throws com.google.api.gax.rpc.ApiException if the remote call fails
   */
  public final Profile createOfflineProfile(CreateOfflineProfileRequest request) {
    return createOfflineProfileCallable().call(request);
  }

  // AUTO-GENERATED DOCUMENTATION AND METHOD.
  /**
   * CreateOfflineProfile creates a new profile resource in the offline mode. The client provides
   * the profile to create along with the profile bytes, the server records it.
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * try (ProfilerServiceClient profilerServiceClient = ProfilerServiceClient.create()) {
   *   CreateOfflineProfileRequest request =
   *       CreateOfflineProfileRequest.newBuilder()
   *           .setParent("parent-995424086")
   *           .setProfile(Profile.newBuilder().build())
   *           .build();
   *   ApiFuture<Profile> future =
   *       profilerServiceClient.createOfflineProfileCallable().futureCall(request);
   *   // Do something.
   *   Profile response = future.get();
   * }
   * }</pre>
   */
  public final UnaryCallable<CreateOfflineProfileRequest, Profile> createOfflineProfileCallable() {
    return stub.createOfflineProfileCallable();
  }

  // AUTO-GENERATED DOCUMENTATION AND METHOD.
  /**
   * UpdateProfile updates the profile bytes and labels on the profile resource created in the
   * online mode. Updating the bytes for profiles created in the offline mode is currently not
   * supported: the profile content must be provided at the time of the profile creation.
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * try (ProfilerServiceClient profilerServiceClient = ProfilerServiceClient.create()) {
   *   UpdateProfileRequest request =
   *       UpdateProfileRequest.newBuilder()
   *           .setProfile(Profile.newBuilder().build())
   *           .setUpdateMask(FieldMask.newBuilder().build())
   *           .build();
   *   Profile response = profilerServiceClient.updateProfile(request);
   * }
   * }</pre>
   *
   * @param request The request object containing all of the parameters for the API call.
   * @throws com.google.api.gax.rpc.ApiException if the remote call fails
   */
  public final Profile updateProfile(UpdateProfileRequest request) {
    return updateProfileCallable().call(request);
  }

  // AUTO-GENERATED DOCUMENTATION AND METHOD.
  /**
   * UpdateProfile updates the profile bytes and labels on the profile resource created in the
   * online mode. Updating the bytes for profiles created in the offline mode is currently not
   * supported: the profile content must be provided at the time of the profile creation.
   *
   * <p>Sample code:
   *
   * <pre>{@code
   * try (ProfilerServiceClient profilerServiceClient = ProfilerServiceClient.create()) {
   *   UpdateProfileRequest request =
   *       UpdateProfileRequest.newBuilder()
   *           .setProfile(Profile.newBuilder().build())
   *           .setUpdateMask(FieldMask.newBuilder().build())
   *           .build();
   *   ApiFuture<Profile> future = profilerServiceClient.updateProfileCallable().futureCall(request);
   *   // Do something.
   *   Profile response = future.get();
   * }
   * }</pre>
   */
  public final UnaryCallable<UpdateProfileRequest, Profile> updateProfileCallable() {
    return stub.updateProfileCallable();
  }

  @Override
  public final void close() {
    stub.close();
  }

  @Override
  public void shutdown() {
    stub.shutdown();
  }

  @Override
  public boolean isShutdown() {
    return stub.isShutdown();
  }

  @Override
  public boolean isTerminated() {
    return stub.isTerminated();
  }

  @Override
  public void shutdownNow() {
    stub.shutdownNow();
  }

  @Override
  public boolean awaitTermination(long duration, TimeUnit unit) throws InterruptedException {
    return stub.awaitTermination(duration, unit);
  }
}
